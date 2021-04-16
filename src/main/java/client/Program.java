package client;

public class Program {
    public static void main(String[] args) {
        var map = new HexagonalMap(800, 600, 30);
        var players = new Player[] {
            new Player(0, new Anthill(new AnthillPlace(new Hexagon[0]), new Resources()))
        };
        var game = new Game(map, players);
        game.start();
    }


}

