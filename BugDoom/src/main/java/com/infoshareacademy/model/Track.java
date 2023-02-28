package com.infoshareacademy.model;

public class Track extends ControlPoint{
    private String trackId;
    private ControlPoint startPoint;
    private ControlPoint endPoint;
    private ControlPoint checkpoint;
    private int length;
    private String difficulty;
    private String location;

    public String getTrackId() {
        return trackId;
    }

    public void setId(String trackId) {
        this.trackId = trackId;
    }

    public ControlPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(ControlPoint startPoint) {
        this.startPoint = startPoint;
    }

    public ControlPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(ControlPoint endPoint) {
        this.endPoint = endPoint;
    }

    public ControlPoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(ControlPoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void drawMap() {
        System.out.println("| --->Y\nV X");
        for (int i = 0; i < tablica.length; i++) { // wiersze
            for (int j = 0; j < tablica.length; j++) { // kolumny
                System.out.print(tablica[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void createCheckpoints() {
        boolean notEqualStartAndEndAndCheckpoints = true;

        do {

            try {
                System.out.println("zdefiniuj checkpoint");
                setCoordinateXY();
                if (!(tablica[x][y].equals("STR")) && !(tablica[x][y].equals("END"))
                        && !(tablica[x][y].equals("CHP"))) {
                    tablica[x][y] = "CHP";
                    notEqualStartAndEndAndCheckpoints = false;
                    drawMap();
                } else {
                    System.out.println("Punkt musi mieć inne współrzędne od wcześniej zdefiniowanych");
                }
            } catch (Exception e) {
                System.out.println("musisz wprowadzić liczbę");
            }
        } while (notEqualStartAndEndAndCheckpoints);
    }

    public void createStart() {
        System.out.println("zdefiniuj punkt startowy");
        setCoordinateXY();
        tablica[x][y] = "STR";
        drawMap();
    }

    public void createEnd() {
        System.out.println("zdefiniuj punkt końcowy");
        boolean notEqualStart = true;
        do {
            try {
                setCoordinateXY();
                if (!(tablica[x][y].equals("STR"))) {
                    tablica[x][y] = "END";
                    notEqualStart = false;
                } else {
                    System.out.println("Meta musi mieć inne współrzędne niż start");
                }
            } catch (Exception e) {
                System.out.println("musisz wprowadzić liczbę");
            }
        } while (notEqualStart);
        drawMap();
    }
    public void createMap(){
        getTablica();
        drawMap();
        createStart();
        createEnd();
    }
}
