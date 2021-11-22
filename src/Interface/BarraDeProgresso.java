package Interface;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;

public class BarraDeProgresso extends JFrame{
	
	private JProgressBar barra = new JProgressBar();
	private long maximo;
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public BarraDeProgresso(int maximo){
		ConfigurarJanela();
		barra.setBounds(40, 15, 300, 30);
		barra.setStringPainted(true);
		barra.setMaximum(maximo);
		barra.setForeground(new Color(26,148,49));
		add(barra);
		this.maximo = maximo;
	}
	
	public void ConfigurarJanela() {
		setTitle("Barra de progresso");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,100);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setValor(int add) {
		barra.setValue(add);
		if(barra.getValue() == this.maximo) {
			JOptionPane.showMessageDialog(null, "Convertido");
			setVisible(false);
		}
	}

	
}
