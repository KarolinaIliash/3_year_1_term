package com.company;

public class Plane {
    private String model;
    private String origin;
    static public class Chars{
        private String type;
        private int seats;
        private boolean ammunition;
        private int rackets;
        private boolean radar;
        public void setType(String type){
            this.type = type;
        }
        public void setSeats(int seats){
            this.seats = seats;
        }
        public void setAmmunition(boolean ammunition){
            this.ammunition = ammunition;
        }
        public void setRackets(int rackets){
            this.rackets = rackets;
        }
        public  void setRadar(boolean radar){
            this.radar = radar;
        }
        @Override
        public String toString(){
            String ammunitionS = (ammunition == true) ? "yes\nRackets " + rackets: "no";
            String radarS = (radar == true) ? "yes":"no" ;
            return "\nChars\nType " + type +"\nSeats "+seats + "\nAmmunition " + ammunitionS + "\nRadar " + radarS;
        }
    }
    static public class Parameters{
        private double height;
        private double length;
        private double wingspan;
        public void setHeight(double height){
            this.height = height;
        }
        public void setLength(double length){
            this.length = length;
        }
        public void setWingspan(double wingspan){
            this.wingspan = wingspan;
        }
        @Override
        public String toString(){
            return "\nParameters\nHeight " + height + "\nLength " + length + "\nWingspan " + wingspan;
        }
    }
    private Chars chars;
    private Parameters parameters;
    private double price;

    public Plane(){
        chars = new Chars();
        parameters = new Parameters();
    }

    public void setModel(String model){
        this.model = model;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public  void setPrice(double price){
        this.price = price;
    }
    public Chars getChars(){
        return chars;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return "\nModel "+ model+"\nOrigin " + origin + chars.toString() + parameters.toString() + "\nPrice " + price +'\n';
    }
}
