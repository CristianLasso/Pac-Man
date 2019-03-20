package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Move;
import model.PacMan;
import thread.ControlThread;
import thread.MoveThread;

public class PacManController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane;

    @FXML
    private MenuBar bar;

    @FXML
    private Menu load;

    @FXML
    private MenuItem lvl0;

    @FXML
    private MenuItem lvl1;

    @FXML
    private MenuItem lvl2;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem scores;

    @FXML
    private MenuItem about;

    @FXML
    private Label score;
    
    private Stage stage;
    
    private ArrayList<PacMan> pacs;
    private ArrayList<Arc> arcPacs;

    @FXML
    void scores(ActionEvent event) {

    }
    
    @FXML
    void about(ActionEvent event) {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("Pac-Man");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(" ABOUT THE GAME \n\n"+
    	"In the load option choose the level that you wat to play.\n"+
    	"You have to click the Pac-Man's before they hit a border of the pane "+
    	"every time that they hit a border the score increase one point and you "+
    	"have to obtain the less points number that you can "+
    	"so you can be in our hall of fame"+
    	" \n\n Have fun.");
    	info.show();
    }

    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void load0(ActionEvent event) throws IOException{
    	String path = "txts/level0.txt";
    	loadLevel(path);
    }

    @FXML
    void load1(ActionEvent event) {

    }

    @FXML
    void load2(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }
    
    /*public boolean pacManClose(boolean keep) {
    	for(int i=0; i<arcPacs.size(); i++) {
    	if(keep) {
    		arcPacs.get(i).setLength(arcPacs.get(i).getLength()+5);
    		arcPacs.get(i).setStartAngle(arcPacs.get(i).getStartAngle()-2);
    	}
    	if(arcPacs.get(i).getLength()>=360) {
    		keep = false;
    	}
    	}
    	return keep;
    }
    
    public boolean pacManOpen(boolean keep) {
    	for(int i=0; i<arcPacs.size(); i++) {
    	if(!keep) {
    		arcPacs.get(i).setLength(arcPacs.get(i).getLength()-5);
    		arcPacs.get(i).setStartAngle(arcPacs.get(i).getStartAngle()+2);
    	}
    	if(arcPacs.get(i).getLength()<270) {
    		keep = true;
    	}
    	}
    	return keep;
    }*/
    
    /*public boolean right(boolean right, Arc pac) {
    	if(pac.getLayoutX()>=pane.getWidth()-pac.getRadiusX()) {
    		right = false;
    		pac.setRotate(180);
		}
    	if(right) {
    		pac.setLayoutX(pac.getLayoutX()+5);
    	}
    	return right;
    }
    
    public boolean left(boolean right, Arc pac) {
    	if(pac.getLayoutX()<=pac.getRadiusX()) {
    		right = true;
    		pac.setRotate(0);
		}	
    	if(!right) {
    		pac.setLayoutX(pac.getLayoutX()-5);
    	}
		return right;
    }*/
    
    public void loadLevel(String path) {
    	int radius = 0;
    	int layoutX = 0;
    	int layoutY = 0;
    	int wait = 0;
    	String direction = "";
    	int bounces = 0;
    	boolean stoped = false;
    	
    	try {
    		
    		File f = new File(path);
    		FileReader fr = new FileReader(f);
    		BufferedReader br = new BufferedReader(fr);
		
    		String line = br.readLine();
    		line = br.readLine();
    		line = br.readLine();
    		line = br.readLine();
    		
    		while(line != null) {
    			
				String[] variables = line.split("-");
				radius = Integer.parseInt(variables[0]);
				layoutX = Integer.parseInt(variables[1]);
				layoutY = Integer.parseInt(variables[2]);
				wait = Integer.parseInt(variables[3]);
				direction = variables[4];
				bounces = Integer.parseInt(variables[5]);
				stoped = Boolean.parseBoolean(variables[6]);
				
				Move move;
				
				if(direction.equals("RIGHT")) {
					move = Move.RIGHT;
				}else if(direction.equals("LEFT")) {
					move = Move.LEFT;
				}else {
					move = Move.LEFT;
				}
				
				if(variables[6]=="false") {
					stoped = false;
				}else {
					stoped = true;
				}
				
				PacMan pac = new PacMan(radius, layoutX, layoutY, wait, bounces, stoped, move);
				pacs.add(pac);
				line = br.readLine();
			}
    		
			br.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	for(int i = 0 ; i < pacs.size() ; i++) {
    		Arc pacm = new Arc(pacs.get(i).getLayoutX(), pacs.get(i).getLayoutY(), pacs.get(i).getRadius(), pacs.get(i).getRadius(), 32, 300);
    		pacm.setFill(Color.YELLOW);
    		pacm.setStroke(Color.BLACK);
    		pacm.setStrokeWidth(3);
    		pacm.setType(ArcType.ROUND);
    		pane.getChildren().add(pacm);
    		arcPacs.add(pacm);
    		
        	MoveThread bt = new MoveThread(this, pacs.get(i));
        	bt.setDaemon(true);
        	bt.start();
    	}
    	
    	
    }
    
    public void update() {
    	for (int id = 0; id < arcPacs.size(); id++) {
    		arcPacs.get(id).setLayoutX(pacs.get(id).getLayoutX());
    		arcPacs.get(id).setLayoutY(pacs.get(id).getLayoutY());			
		}
    }
    
    public double getWith() {
		return stage.getScene().getWidth();
	}
    
    public void setStage(Stage st) {
    	stage = st;
    }
    
    public ArrayList<Arc> getList() {
    	return arcPacs;
    }

    @FXML
    void initialize() {
    	pacs = new ArrayList<PacMan>();
    	arcPacs = new ArrayList<Arc>();
    	ControlThread pacmt = new ControlThread(this);
    	pacmt.setDaemon(true);
    	pacmt.start();
    }
}


