/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Mal
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({haunted.LobbyTest.class, haunted.LevelTest.class, haunted.MessageTest.class, haunted.ObstacleTest.class, haunted.FXMLHauntedControllerTest.class, haunted.GameTest.class, haunted.GhostTest.class, haunted.HauntedTest.class, haunted.HumanTest.class, haunted.PlayerTest.class, haunted.CharacterTest.class, haunted.GameLobbyTest.class})
public class HauntedSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
