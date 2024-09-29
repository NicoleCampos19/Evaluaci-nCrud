/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.frmInformacionCocinero;
import Vista.frmCocinero;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author emily
 */
public class ctrlBienvenida implements MouseListener{
    private frmCocinero Vista;
    
    public ctrlBienvenida(frmCocinero Vista){
       this.Vista = Vista;
       
       Vista.btnContinuar.addMouseListener(this);
    }
    
    private void abrirFrmInformacionCocinero() {
        frmInformacionCocinero infococi = new frmInformacionCocinero();
        infococi.initCocinero();
        Vista.dispose();
    }
   

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnContinuar) {
            abrirFrmInformacionCocinero(); 
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
