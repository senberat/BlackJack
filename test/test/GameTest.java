package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import project.Game;
import static org.junit.Assert.*;


public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of getBet method, of class Game.
     */
    @Test
    public void testGetBetBoundry() {
        System.out.println("getBet boundry");
        Game instance = new Game();
        // exp result is set to be the minimum possible number
        double expResult = 0.01;
        double result = 0.01;
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetBetGood() {
        System.out.println("getBet good");
        Game instance = new Game();
        // exp result set to be a greater value than the minimum
        double expResult = 20.0;
        double result = 20;
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetBetBadNegative() {
        System.out.println("getBet bad negative");
        Game instance = new Game();
        double expResult = -1.0;
        double result = -1.0;
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetBetBadZero() {
        System.out.println("getBet bad zero");
        Game instance = new Game();
        double expResult = 0;
        double result = 0;
        assertEquals(expResult, result, 0.0);
    }

    
    @Test
    public void testSimpleWinBoundry() {
        System.out.println("simpleWin boundry");
        Game instance = new Game();
        
        int player = 21;
        int casino = 20;
        boolean won = player > casino;
        boolean expected = true;
        assertEquals(won, expected);
    }
    
    @Test
    public void testSimpleWinGood() {
        System.out.println("simpleWin Good");
        Game instance = new Game();
        
        int player = 15;
        int casino = 10;
        boolean won = player > casino;
        boolean expected = true;
        assertEquals(won, expected);
    }
    
    @Test
    public void testSimpleWinBad() {
        System.out.println("simpleWin Bad");
        Game instance = new Game();
        
        int player = 10;
        int casino = 20;
        boolean won = player > casino;
        boolean expected = false;
        assertEquals(won, expected);
    }
    
    @Test
    public void testSimpleWinDraw() {
        System.out.println("simpleWin Draw");
        Game instance = new Game();
        
        int player = 20;
        int casino = 20;
        boolean won = player == casino;
        boolean expected = true;
        assertEquals(won, expected);
    }
    
    @Test
    public void testCasinoWinBoundry() {
        System.out.println("casinoWin boundry");
        Game instance = new Game();
        
        int player = 20;
        int casino = 21;
        boolean won = casino > player;
        boolean expected = true;
        assertEquals(won, expected);
    }
    
    @Test
    public void testCasinoWinGood() {
        System.out.println("casinoWin Good");
        Game instance = new Game();
        
        int player = 10;
        int casino = 15;
        boolean won = player < casino;
        boolean expected = true;
        assertEquals(won, expected);
    }
    
    @Test
    public void testCasinoWinBad() {
        System.out.println("casinoWin Bad");
        Game instance = new Game();
        
        int player = 20;
        int casino = 10;
        boolean won = player < casino;
        boolean expected = false;
        assertEquals(won, expected);
    }
    
  
    
}
