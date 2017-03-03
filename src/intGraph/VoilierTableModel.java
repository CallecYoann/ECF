/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraph;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author callec
 */
public class VoilierTableModel extends AbstractTableModel {

    private final String[] colonnes = {"Nom", "Numéro de voile", "Proprietaire", "Classe"};
    private List<Voilier> voiliers;

    public VoilierTableModel() {
        this.voiliers = new ArrayList<>();
    }

    public VoilierTableModel(List<Voilier> voiliers) {
        this.voiliers = voiliers;
    }

    @Override
    public String getColumnName(int column) {
        return colonnes[column];
    }

    @Override
    public int getRowCount() {
        return voiliers.size();
    }

    public void addVoilier(Voilier v) {
        this.voiliers.add(v);
    }
    
    @Override
    public int getColumnCount() {
        return colonnes.length;
    }
    
    public void clearVoilier(Voilier v) {
        this.voiliers = new ArrayList<>();
        this.fireTableDataChanged();
    }
    
    public void setVoilier(List<Voilier> voiliers) {
        this.voiliers = voiliers;
    }

    public Voilier getVoilier(int rowIndex) {
        return voiliers.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return voiliers.get(rowIndex).getNom_voilier();

            case 1:
                return voiliers.get(rowIndex).getNum_voile();

            case 2:
                return voiliers.get(rowIndex).getProprietaire().getNom() + voiliers.get(rowIndex).getProprietaire().getPrenom();

            case 3:
                return voiliers.get(rowIndex).getClasse().getNom_classe();

            default:
                throw new IllegalArgumentException();
        }

    }

}
