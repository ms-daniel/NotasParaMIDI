package Interface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("unused")
public class Principal {

		private JFrame frame;
		private int Esc;
		
		private JLabel LabPesFile;
		private JButton PesFile;
		private JTextField TexPesFile;
		
		private JLabel LabPesDest;
		private JButton PesDest;
		private JTextField TexPesDest;
		
		private JLabel link = new JLabel("Para encontrar cifras melodicas recomendamos"
				+ "\n este site (Deixe as quebras de linha e apenas as notas)");

		private Desktop Desktop;
		
		private JButton Conv;
		private JButton tips;
		private ImageIcon tip_icon = new ImageIcon("resources/tip-icon.png");
		private ImageIcon tip_icon2 = new ImageIcon("resources/tip-icon2.png");
		
		private JButton gitB;
		private ImageIcon git  = new ImageIcon("resources/git-icon.png");
		private ImageIcon git2 = new ImageIcon("resources/git-icon2.png");
		private ImageIcon janelaIcon = new ImageIcon("resources/janelaIcon.png");
		
		private JFileChooser escolher; //seletor de arquivo		
		private String DirOrig = null;		//diretorio de origem do arquivo
		private String DirSalv = null;  	//diretorio onde ira salvar o novo arquivo
		private String nomeArq;		//nome do arquivo
		
		private Trans converter;
		
		private char entrada;
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Principal window = new Principal();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		

		/**
		 * Create the application.
		 * @throws IOException 
		 */
		public Principal() throws IOException {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 * @throws IOException 
		 */
		private void initialize() throws IOException {
			frame = new JFrame("Transforma Notas Para Ascii");
			frame.setBounds(450, 230, 450, 225);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setIconImage(janelaIcon.getImage());
			frame.getContentPane().setLayout(null);
			
			//cria e abre a tela para selecionar o arquivo
			escolher = new JFileChooser(); 
			escolher.setCurrentDirectory(new File("."));
			escolher.setBounds(0,0,400,270);
			
			//parte da pesquisa onde esta o arquivo de origem
			//nome da label
			LabPesFile = new JLabel("Arquvio de origem:");
			LabPesFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
			LabPesFile.setBounds(15, 3, 293, 20);
			frame.getContentPane().add(LabPesFile);
			
			//a box de texto onde ficara o diretorio selecionado
			TexPesFile = new JTextField();
			TexPesFile.setBounds(15, 25, 290, 25);
			frame.getContentPane().add(TexPesFile);
			TexPesFile.setEditable(false);
			TexPesFile.setBackground(new Color(255, 255, 255));
			TexPesFile.setColumns(10);
			
			//botao de pesquisa
			PesFile = new JButton("Selecionar");
			PesFile.setBounds(315, 25, 100, 25);
			PesFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
			frame.getContentPane().add(PesFile);
			
			//parte da pesquisa do diretorio onde salvar
			LabPesDest = new JLabel("Diretório de destino:");
			LabPesDest.setFont(new Font("Tahoma", Font.PLAIN, 13));
			LabPesDest.setBounds(15, 55, 293, 20);
			frame.getContentPane().add(LabPesDest);
			
			TexPesDest = new JTextField();
			TexPesDest.setBounds(15, 77, 290, 25);
			frame.getContentPane().add(TexPesDest);
			TexPesDest.setEditable(false);
			TexPesDest.setBackground(new Color(255, 255, 255));
			TexPesDest.setColumns(10);
			
			PesDest = new JButton("Selecionar");
			PesDest.setBounds(315, 77, 100, 25);
			PesDest.setCursor(new Cursor(Cursor.HAND_CURSOR));
			frame.getContentPane().add(PesDest);
			PesDest.setEnabled(false);
			
			//botaõ para converter
			Conv = new JButton("CONVERTER");
			Conv.setBounds(155, 120, 120, 50);
			Conv.setBackground(new Color(26,148,49));
			Conv.setCursor(new Cursor(Cursor.HAND_CURSOR));
			frame.getContentPane().add(Conv);
			Conv.setEnabled(false);
			
			//botao de dicas
			tips = new JButton();
			tips.setIcon(tip_icon);
			tips.setContentAreaFilled(false);
			tips.setBorderPainted(false);
			tips.setCursor(new Cursor(Cursor.HAND_CURSOR));
			tips.setFocusable(false);
			tips.setToolTipText("Dicas");
			tips.setBounds(35, 118, 50, 50);
			frame.getContentPane().add(tips);
			
			//botao criador
			gitB = new JButton();
			gitB.setIcon(git);
			gitB.setContentAreaFilled(false);
			gitB.setBorderPainted(false);
			gitB.setCursor(new Cursor(Cursor.HAND_CURSOR));
			gitB.setFocusable(false);
			gitB.setToolTipText("Criador");
			gitB.setBounds(350, 118, 50, 50);
			frame.getContentPane().add(gitB);
			
			//configurações para link cifra melodica
			link.setForeground(Color.BLUE.darker());
			link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			//ações quando passa o mouse por cima
			link.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	try {
			            Desktop.getDesktop().browse(new URI("http://www.ciframelodica.com.br"));
			             
			        } catch (IOException | URISyntaxException e1) {
			            e1.printStackTrace();
			        }
			    }
			 
			    @Override
			    public void mouseEntered(MouseEvent e) {
			    	link.setText("<html><a href=''>Para encontrar cifras melodicas recomendamos"
			    						+ " este site (Deixe as quebras de linha e apenas as notas)" + "</a></html>");
			    }
			 
			    @Override
			    public void mouseExited(MouseEvent e) {
			    	link.setText("Para encontrar cifras melodicas recomendamos"
    						+ " este site (Deixe as quebras de linha e apenas as notas)");
			    }
			});
			
			//ações do mouse no icone de dicas
			tips.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseEntered(MouseEvent e) {
			    	tips.setIcon(tip_icon2);
			    }
			 
			    @Override
			    public void mouseExited(MouseEvent e) {
			    	tips.setIcon(tip_icon);
			    }
			});
			
			gitB.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseEntered(MouseEvent e) {
			    	gitB.setIcon(git2);
			    }
			 
			    @Override
			    public void mouseExited(MouseEvent e) {
			    	gitB.setIcon(git);
			    }
			});
			
			gitB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Desktop.getDesktop().browse(new URI("https://github.com/danknightt"));
					} catch (IOException | URISyntaxException e1) {
				            e1.printStackTrace();
				    }
				}
			});
			
			PesFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "Arquivo de texto", "txt");
					escolher.setFileFilter(filter);
					escolher.setFileSelectionMode(0);
				
					Esc = escolher.showOpenDialog(frame);
					if(Esc == JFileChooser.APPROVE_OPTION) {
						//pega o diretorio do arquivo de origem
						DirOrig = escolher.getSelectedFile().getAbsolutePath();
						DirOrig = DirOrig.replace("\\", "/");
						TexPesFile.setText(DirOrig);
						//pega o nome do arquivo
						nomeArq = escolher.getSelectedFile().getName();
						
						//mas antes vamos remover esse trecho do diretorio para salvarmos no mesmo local
						DirSalv = DirOrig.replace(nomeArq, "");
						//continuemos
						nomeArq = nomeArq.replace(".txt", "");
						nomeArq += "-TdN.txt";
						
						DirSalv = DirSalv+nomeArq;
						TexPesDest.setText(DirSalv);
						PesDest.setEnabled(true);
						Conv.setEnabled(true);
					}
				}
			});
			
			//ação do botão de salvar em outro diretorio
			PesDest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//muda o modo de seleção de arquiivo para modo de seleção de diretorio
					escolher.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					escolher.setFileFilter(null);
					escolher.setDialogTitle("Escolha o diretório");
					Esc = escolher.showOpenDialog(frame);
					
					if(Esc == JFileChooser.APPROVE_OPTION) {
						//pega o diretorio do arquivo de origem
						DirSalv = escolher.getSelectedFile().getAbsolutePath();
						DirSalv = DirSalv+'\\'+nomeArq;
						DirSalv = DirSalv.replace("\\", "/");
						TexPesDest.setText(DirSalv);
					}
				}
			});
			
			Conv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						DirOrig = DirOrig.replace("\\", "/");
						
						converter = new Trans();
						converter.setIn(new FileInputStream(DirOrig));
						converter.setOut(new FileOutputStream(DirSalv));
						
						converter.Converter();
					}
					catch (IOException e) {
						System.out.println(e);
					}
				}
			});
			
			tips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, link, "Dica!", 1, tip_icon);	
				}
			});
				
				
			
			
		}
}
