/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.awt.Color;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Evers
 */
public class GameLobby extends UnicastRemoteObject implements IGameLobby {

    private String name, password;
    private int maxPlayers, maxFloors;
    private IPlayer host;    
    private List<IPlayer> players;
    private BasicPublisher basicPublisher;
    private static int gameLobbyNum = 0;
    private String groupID;
    private Lobby lobby;
    private boolean ingame;

    /**
     * maakt een nieuwe gamelobby aan
     *
     * @param name
     * @param password
     * @param host
     * @param maxFloors
     * @param maxPlayers
     * @param lobby
     * @throws java.rmi.RemoteException
     */
    public GameLobby(String name, String password, IPlayer host, int maxFloors, int maxPlayers, Lobby lobby) throws RemoteException, IOException {
        this.lobby = lobby;
        this.name = name;
        this.password = password;
        this.host = host;        
        this.maxPlayers = maxPlayers;
        this.maxFloors = maxFloors;
        this.players = new ArrayList();
        String[] props = new String[1];
        props[0] = "players";
        this.basicPublisher = new BasicPublisher(props);
        players.add(host);
        ingame = false;

        //<socket>
        int groupIdNum = 234567890 + gameLobbyNum;
        String stringConverter = "" + groupIdNum;
        groupID = stringConverter.substring(0, 3) + "." + stringConverter.substring(4, 5) + "." + stringConverter.substring(6, 7) + "." + stringConverter.substring(8, 9);
        gameLobbyNum++;
        System.out.println("Created lobby name:"+this.name+" host: "+this.host.getName());
        //</socket>

    }

    /**
     * als alle spelers klaar zijn dan start de game
     */
    @Override
    public void startGame() throws RemoteException {
        if (players.size() > 2) {
            boolean ready = readycheck();
            if (ready) {
                Game game;
                try {
                    int i = 0;
                    for(IPlayer player : players)
                    {
                       switch(i)
                       {
                           case(0):
                                player.setColor(Color.WHITE);
                           break;
                           case(1):
                                player.setColor(Color.BLACK);
                           break;
                           case(2):
                                player.setColor(Color.GREEN);
                           break;
                           case(3):
                                player.setColor(Color.RED);
                           break;      
                           case(4):
                                player.setColor(Color.BLUE);
                           break;
                           case(5):
                                player.setColor(Color.ORANGE);
                           break;  
                           case(6):
                                player.setColor(Color.MAGENTA);
                           break;                            
                       }
                       i++;
                    }
                    game = new Game(players, maxFloors, groupID, this);
                    ingame = true;
                    System.out.println("game "+this.getName()+" has started");
                    game.startRound();
                    this.basicPublisher.inform(this, "players", null, players);
                } catch (IOException ex) {
                    Logger.getLogger(GameLobby.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * verwijdert een speler uit de gamelobby
     *
     * @param player
     * @return
     */
    @Override
    public synchronized boolean removePlayer(IPlayer player) throws RemoteException {
        boolean exist = false;
        for (IPlayer speler : players) {
            if (player.equals(speler)) {
                exist = true;
            }
        }
        if (exist) {
            players.remove(player);
            this.basicPublisher.inform(this, "players", null, players);
            System.out.println("player "+player.getName()+" was kicked from gamelobby "+this.name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * voegt een speler toe aan de gamelobby
     *
     * @param player
     * @return
     */
    @Override
    public synchronized boolean addPlayer(IPlayer player) throws RemoteException {
        boolean exist = false;
        for (IPlayer speler : players) {
            if (speler == player) {
                exist = true;
            }
        }
        if (exist == false && this.players.size() + 1 <= this.maxPlayers) {
            naamchecker(player);
            players.add((IPlayer) player);
            this.basicPublisher.inform(this, "players", null, players);
            System.out.println("player "+player.getName()+" was added to gamelobby "+this.name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * vraagt de naam op van de gamelobby
     *
     * @return
     */
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    /**
     * vraagt het maximum aantal spelers van de gamelobby op
     *
     * @return
     */
    @Override
    public int getMaxPlayer() throws RemoteException {
        return maxPlayers;
    }

    /**
     * vraagt het maximum aantal vloeren van de gamelobby op
     *
     * @return
     */
    @Override
    public int getMaxFloors() throws RemoteException {
        return maxFloors;
    }

    /**
     * vraagt lijst met spelers die in de gamelobby zit op
     *
     * @return
     */
    @Override
    public List<IPlayer> getPlayers() throws RemoteException {
        return players;
    }

    @Override
    public boolean readycheck() throws RemoteException {
        for (IPlayer speler : players) {
            if (!speler.getReady()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.addListener(remotePropertyListener, string);
    }

    @Override
    public void removeListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.removeListener(remotePropertyListener, string);
    }

    @Override
    public String getGroupID() throws RemoteException {
        return this.groupID;
    }
    
    public Lobby getLobby(){
        return this.lobby;
    }
    
    public boolean getIngame() throws RemoteException
    {
        return ingame;
    }
    
    public IPlayer getHost() throws RemoteException
    {
        return host;
    }
    
    public String getww() throws RemoteException
    {
        return password;
    }
    
    private void naamchecker(IPlayer player) throws RemoteException
    {
        for (IPlayer spelernaam : players)
            {
                if(player.getName().equals(spelernaam.getName()))
                {
                    player.setName(player.getName() + "(1)");
                    naamchecker(player);
                }
            }
    }

    @Override
    public void setHost(IPlayer player) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String naam) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaxFloors(int maxfloors) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaxPlayers(int maxplayers) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}