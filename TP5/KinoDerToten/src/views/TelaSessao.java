package views;

import javax.swing.*;
import controller.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe <b>TelaSessao</b>
 * @author Yago Milagres Passos
 *
 */
public class TelaSessao implements ActionListener, ListSelectionListener{
	private JFrame f = new JFrame("KDT - Sess�es");
	private JLabel titulo = new JLabel("Sess�es");
	private JList<String> listaSessoes;
	private String[] listaDetalhes = new String[50];
	private	JButton criar = new JButton("Criar"); 
	private JButton atualizar = new JButton("Atualizar");
	private JButton voltar = new JButton("Voltar");
	private static ControleDados dados;
	private static ImageIcon background = new ImageIcon("imgs/KinoDerToten.png");
	private static ImageIcon icone = new ImageIcon("imgs/icon.png");
	private static JLabel labelBackground = new JLabel();	
	Font text = new Font("SansSerif", Font.PLAIN, 18);
	Font title = new Font("SansSerif", Font.BOLD, 26);
	Font but = new Font("SansSerif", Font.BOLD ,14);
	
	
	/**
	 * Construtor da Classe <b>TelaSessao</b>. Aqui � um JFrame que mostra a lista de sess�es, com a op��o de edit�-las ou adicionar uma nova.
	 * @param d -> instancia de COntroleDados
	 */
	public TelaSessao(ControleDados d) {
		dados = d;
		
		f.setBounds(500,200,700,500);
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		f.setResizable(false);
		f.setIconImage(icone.getImage());
		
		listaDetalhes = new ControleSessao(dados).displaySessaoOnJList();
		listaSessoes = new JList<String>(listaDetalhes); 
		
		f.add(titulo);
		titulo.setBounds(280,-70,300,250);
		f.add(listaSessoes);
		listaSessoes.setBounds(50,100,600,260);
		f.add(criar);	
		criar.setBounds(210,400,100,50);
		//f.add(descr);
		//descr.setBounds(230, 275, 200, 200);	
		f.add(atualizar);
		atualizar.setBounds(320,400,100,50);
		f.add(voltar);
		voltar.setBounds(500, 400, 100, 50);
		
		f.setIconImage(icone.getImage());
		
		titulo.setForeground(Color.white);
		
		labelBackground.setIcon(background);
		f.add(labelBackground, BorderLayout.CENTER);		
		labelBackground.setPreferredSize(new Dimension(700,500));
		
		listaSessoes.setFont(text);
		titulo.setFont(title);
		criar.setFont(but);
		atualizar.setFont(but);
		voltar.setFont(but);
		
		listaSessoes.addListSelectionListener(this);
		criar.addActionListener(this);
		atualizar.addActionListener(this);
		voltar.addActionListener(this);
	}
	
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();
		
		if(e.getValueIsAdjusting() && src==listaSessoes)
			new TelaDetalheSessao().exibeSessao(dados, listaSessoes.getSelectedIndex());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		
		if(src == criar)
			new TelaDetalheSessao().criaSessao(1, dados);
		
		if(src == atualizar)
			listaSessoes.setListData(new ControleSessao(dados).displaySessaoOnJList());
			listaSessoes.updateUI();
			
		if(src == voltar) {
			f.dispose();
		}
		
	}
	
}
