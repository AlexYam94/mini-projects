package com.ymh;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team<FootballPlayer> adelaideCros = new Team<>("Adelaide Crows");
        adelaideCros.addPlayer(joe);
//      adelaideCros.addPlayer(pat);
//      adelaideCros.addPlayer(beckham);
        System.out.println(adelaideCros.numPlayers());

        Team<BaseballPlayer> baseballPlayerTeam = new Team<>("Chicago Cubs");
        baseballPlayerTeam.addPlayer(pat);

        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.matchResult(fremantle,1,0);
        hawthorn.matchResult(adelaideCros,3,8);

        System.out.println("Ranking: ");
        System.out.println(adelaideCros.getName() + ": " + adelaideCros.ranking());
        System.out.println(melbourne.getName() + ": " + melbourne.ranking());
        System.out.println(fremantle.getName() +": " + fremantle.ranking());
        System.out.println(hawthorn.getName()+ ": "+hawthorn.ranking());

        System.out.println(adelaideCros.compareTo(melbourne));
        System.out.println(adelaideCros.compareTo(hawthorn));
        System.out.println(melbourne.compareTo(adelaideCros));


    }
}
