package sample;

import javafx.scene.control.Label;

import java.util.List;

public interface GymManager {
    public void addNewMember(DefaultMember member) ;
    public boolean deleteMember(int membershipNumber);
    public void printListOfMember();
    public List<DefaultMember> sort();
    public void save();
    public void saveForSearch();


    public void search(String searchValue, Label lbl);




}
