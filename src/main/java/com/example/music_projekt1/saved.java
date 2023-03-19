package com.example.music_projekt1;

public class saved {
    private static String username;
    private static Integer userID;
    private static String gameChosen;
    private static Integer gameIDChosen;
    private static String tournament;
    private static Integer tournamentID;
    private static String teamName;
    private static Integer teamID;

    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username1) {
        username = username1;
    }

    public static Integer getUserID() {
        return userID;
    }
    public static void setUserID(Integer userID1) {
        userID = userID1;
    }

    public static String getGameChosen() {
        return gameChosen;
    }
    public static void setGameChosen(String _gameChosen) {
        gameChosen = _gameChosen;
    }

    public static Integer getGameID () {
        return gameIDChosen;
    }
    public static void setGameIDChosen(Integer _gameIDChosen) {
        gameIDChosen = _gameIDChosen;
    }

    public static String getTournament() {
        return tournament;
    }
    public static void setTournament (String _tournament) {
        tournament = _tournament;
    }

    public static Integer getTournamentID () {
        return tournamentID;
    }
    public static void setTournamentID(Integer _tournamentIDChosen) {
        tournamentID = _tournamentIDChosen;
    }

    public static String getTeam() {
        return teamName;
    }
    public static void setTeam (String _teamName) {
        teamName = _teamName;
    }

    public static Integer getTeamID () {
        return teamID;
    }
    public static void setTeamID(Integer _teamID) {
        teamID = _teamID;
    }


}
