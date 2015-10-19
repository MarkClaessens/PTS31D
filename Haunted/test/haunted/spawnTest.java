/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class spawnTest {
    
    public spawnTest() {
    }
    
    List<Player> players;
    Player player1;
    Player player2;
    Game game;
    int floors;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player1 = new Player("John Doe");
        player2 = new Player("Lucky Luck");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        floors = 5;

        game = new Game(players, floors);
        
        
        Level level = new Level(2);
        List<Obstacle> obstacles = new ArrayList<>();
        Obstacle obstacle = new Obstacle(ObstacleType.WALL, "sprite", new Point2D.Double(200, 400));
        Obstacle obstacle1 = new Obstacle(ObstacleType.WALL, "sprite", new Point2D.Double(600, 300));
        Obstacle obstacle2 = new Obstacle(ObstacleType.WALL, "sprite", new Point2D.Double(900, 800)); 
        Obstacle obstacle3 = new Obstacle(ObstacleType.WALL, "sprite", new Point2D.Double(1300, 900)); 
        obstacles.add(obstacle);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        
        level.setObstacles(obstacles);
        game.setCurrentLevel(level);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSpawn(){
        List<Player> playersReturned = game.bindCharactersToPlayers();
        
    }
}
