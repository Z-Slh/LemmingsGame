package controlleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

import model.BlockConstructor;
import model.Cell;
import model.CircleGameObject;
import model.GameObject;
import model.CellType;
import model.Lemming;
import model.RectGameObject;
import model.Utils;
import vue.CustomMouseListener;
import vue.Gameplay;


public class Controlleur {

	private Gameplay gameplay;

    private int lemmingsDead = 0;    
    private int lemmingsEscaped = 0;

	private String lastStateClicked = "MA";
	
	private static Random randomGenerator = new Random();
	
	private int delay = 500;
	private int passed_time = 0;
	private boolean game_is_running = false;
	private final int lemmingsToGenerate = 20;
	private Timer timer;
	
	private List<GameObject> entries       = new ArrayList<>();	
	private List<GameObject> exits         = new ArrayList<>();
	private List<GameObject> teleporters   = new ArrayList<>();	
	private List<Lemming>    lemmings      = new ArrayList<>();
	private List<Cell>       cellsToRemove = new ArrayList<>();

	private Map<String, Cell> map = new HashMap<>();	
	private List<List<GameObject>> viewables  = new ArrayList<>();
	private BlockConstructor blockConstructor = new BlockConstructor(map, this);

	private final Color TELEPORTER_COLOR = new Color(51, 173, 255);
	private final Color ENTERIES_COLOR = Color.GREEN;
	private final Color EXITS_COLOR = Color.ORANGE;

		
	public Controlleur(Gameplay gameplay) {
		
		this.gameplay = gameplay;
		
		gameplay.setControlleur(this);
		gameplay.addMouseListener(new CustomMouseListener(this));
	
		setButtonsListeners(gameplay.getButtons());
		initMap();
		initEntries();
		initExits();
		initTeleporters();
		startGame();
	}
	

	
	private void startGame() {
		game_is_running = true;
		generateLemming();
		gameplay.invalidate();

		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameLoop();
			}

		});

		timer.start();
	}
	
	private void gameLoop() {
		if (!game_is_running) {
			timer.stop();
			return;
		}
	
		passed_time += delay;
	
		ListIterator<Lemming> iter = lemmings.listIterator();
		while (iter.hasNext()) {
			Lemming lemming = iter.next();
	
			if (lemming.touchExits()) {
				lemmingsEscaped++;
				iter.remove(); 
				gameplay.updateCounters(countLemmingsInGame(), lemmingsDead, lemmingsEscaped); // Mise à jour immédiate
				continue;
			}
	
			if (passed_time < (lemming.getIndex() + 1) * 2 * delay) {
				continue;
			}
	
			lemming.doAction();
	
			if (!lemming.isAlive()) {
				lemmingsDead++;
				iter.remove();
				gameplay.updateCounters(countLemmingsInGame(), lemmingsDead, lemmingsEscaped); // Mise à jour immédiate
			}
		}
	
		gameplay.updateCounters(countLemmingsInGame(), lemmingsDead, lemmingsEscaped);
		gameplay.repaint();
		removeCells();
	}
	
	private void removeCells() {
		for(Cell cell : cellsToRemove) {
			String key = getKey(map, cell);
			map.remove(key);			
		}
		cellsToRemove.clear();
	}



	private void generateLemming() {
		for (int i = 0; i < lemmingsToGenerate; i++) {
			GameObject randomEnterie = getRandomGameObjectFromList(entries);
			Lemming newLemming = new Lemming(randomEnterie.getX(), randomEnterie.getY() - Utils.CELL_SIZE, Utils.CELL_SIZE, Utils.CELL_SIZE, Color.GREEN, this, i);	
			newLemming.register(gameplay); 
			lemmings.add(newLemming);
		}
	}
	
	
	private void initMap() {
		
		int[][] values = {
			{5, 7, 5, 4},
			{10, 7, 15, 1},
			{0, 3, 5, 1},
			{14, 2, 1, 6},
			{15, 2, 11, 1},	
			{13, 3, 1, 1},
			{4, 2, 1, 1},
			{0, 14, 10, 1},
			{2, 2, 1, 1},
		};
		
		blockConstructor.construct(values, CellType.Type.DestructibleNoActionCell, Color.BLACK);
		blockConstructor.construct(0, Utils.FLOOR_START, Utils.NUMBER_OF_CELLS_IN_A_LINE, 2, CellType.Type.NotDestructibleCell, Color.GREEN);
		blockConstructor.construct(10, 8, 1, 1, CellType.Type.LemmingRemoverCell, Color.DARK_GRAY);
		blockConstructor.construct(14, 6, 1, 1, CellType.Type.GeneratorCell, Color.BLUE);
		
	}


	private void initEntries() {
		entries.add(new RectGameObject(0, 0, Utils.CELL_SIZE, 2, ENTERIES_COLOR));
		entries.add(new RectGameObject(22 * Utils.CELL_SIZE, 0, Utils.CELL_SIZE, 2, ENTERIES_COLOR));
		viewables.add(entries);
	}

	private void initExits() {
		exits.add(new CircleGameObject((Utils.NUMBER_OF_CELLS_IN_A_LINE-9) * Utils.CELL_SIZE, Utils.CELL_SIZE * 6, Utils.CELL_SIZE, Utils.CELL_SIZE, EXITS_COLOR));
		exits.add(new CircleGameObject((Utils.NUMBER_OF_CELLS_IN_A_LINE-1) * Utils.CELL_SIZE, Utils.CELL_SIZE , Utils.CELL_SIZE, Utils.CELL_SIZE, EXITS_COLOR));		
		viewables.add(exits);
	}

	
    private <K, V> K getKey(Map<K, V> map, V value) {
    	if(value == null) {return null;}
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	private void initTeleporters() {
		teleporters.add(new CircleGameObject((Utils.NUMBER_OF_CELLS_IN_A_LINE-5) * Utils.CELL_SIZE, Utils.CELL_SIZE * 3, Utils.CELL_SIZE, Utils.CELL_SIZE, TELEPORTER_COLOR));
		teleporters.add(new CircleGameObject(0, Utils.CELL_SIZE * 13, Utils.CELL_SIZE, Utils.CELL_SIZE, TELEPORTER_COLOR));		
		viewables.add(teleporters);
	}
	
	
	
	private void resetGame() {
		timer.stop();
		lemmings.clear();
		map.clear();
		resetCounters(); 
		lastStateClicked = "MA";
		initMap();
		initEntries();
		initExits();
		initTeleporters();
		passed_time = 0;
		startGame();
	}


	private void resetCounters() {
		lemmingsDead = 0;     
		lemmingsEscaped = 0;  
		gameplay.updateCounters(countLemmingsInGame(), lemmingsDead, lemmingsEscaped); // Mets à jour l'affichage
	}


	private boolean isWithinMap(Lemming lemming) {
		return lemming.getX() >= 0 && lemming.getX() < Utils.JFRAME_WIDTH &&
			   lemming.getY() >= 0 && lemming.getY() < Utils.JFRAME_HEIGHT;
	}

	
	private int countLemmingsInGame() {
		int count = 0;
		for (Lemming lemming : lemmings) {
			if (lemming.isAlive() && isWithinMap(lemming)) {
				count++;
			}
		}
		return count;
	}
	

	public void setButtonsListeners(JButton[] btnList) {
				
		for(JButton btn : btnList) {
			btn.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(btn.getText().equals("*")) {resetGame();}
					else{setLastStateClicked(btn.getText());}
				}

			});
		}
		
	}
	
	
	private void setLastStateClicked(String state) {
		lastStateClicked = state;
	}

	public String getLastStateClicked() {
		return lastStateClicked;
	}
	

	public int[] getCoordinatesFromCell(Cell cell) {
		String k = getKey(map, cell);
		int[] coord = getCoordinatesFromKey(k); 
		return coord;
	}
	
	public Cell searchForCell(int x, int y) {	
		for (Cell cell : map.values()) {
			if(cell.getX() == x && cell.getY() == y) {
				return cell;
			}
		}
		return null;
	}
	
	
	public void addCellToRemove(Cell cell) {
		cellsToRemove.add(cell);
	}
	
	public GameObject getRandomGameObjectFromList(List<GameObject> l) {
        int index = randomGenerator.nextInt(l.size());
        GameObject go = l.get(index);
        return go;
	}
	
	
	public String stringifyAndConcat(int i, int j) {
		return (Integer.toString(i) + "_" + Integer.toString(j));
	}
	
	public int[] getCoordinatesFromKey(String str) {
		
		String[] split = str.split("_");
		String s1 = split[0];
		String s2 = split[1];
		
		int x = Integer.valueOf(s1) * Utils.CELL_SIZE;
		int y = Integer.valueOf(s2) * Utils.CELL_SIZE;
		
		
		return new int[] {x, y};
	}


	public Map<String, Cell> getMap() {
		return map;
	}

	public List<Lemming> getLemmings() {
		return lemmings;
	}
	
	public Cell getFromMap(int x, int y) {
		return getFromMap(stringifyAndConcat(x, y));
	}

	private Cell getFromMap(String key) {
		return map.get(key);
	}

	public List<GameObject> getExits() {
		return exits;
	}
	
	public List<GameObject> getEntries() {
		return entries;
	}


	public List<GameObject> getTeleporters() {
		return teleporters;
	}

	
	public List<List<GameObject>> getViewables() {
		return viewables;
	}
	
	public BlockConstructor getBlockConstructor() {
		return blockConstructor;
	}

}