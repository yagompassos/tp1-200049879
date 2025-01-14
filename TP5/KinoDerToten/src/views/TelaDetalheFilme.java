package views;

import javax.swing.*;
import controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe <b>TelaDetalheFilme</b> s� � acessada atrav�s da classe <b>TelaFilme</b>.
 * Dependendo se o usu�rio clicou em editar ou adicionar um novo Filme.
 * @author Yago Milagres Passos
 *
 */
public class TelaDetalheFilme implements ActionListener{
	private JFrame f = new JFrame();
	//private JPanel p = new JPanel();
	private JLabel labelTitle = new JLabel("T�tulo:");
	private JLabel labelDataLanc = new JLabel("Lancamento:");
	private JLabel labelDuracao = new JLabel("Dura��o:");
	private JLabel labelLing = new JLabel("Linguagem:");
	private JLabel labelClassInd = new JLabel("Classifica��o Indicativa:");
	private JLabel labelGen = new JLabel("G�nero:");
	private JTextField valorTitle = new JTextField();
	private JTextField valorDataLanc = new JTextField();
	private JTextField valorDuracao = new JTextField();
	private JRadioButton lingDub = new JRadioButton("Dublado");
	private JRadioButton lingLeg = new JRadioButton("Legendado");
	//private JTextField valorClassInd = new JTextField();
	private JComboBox<String> valorClassInd = new JComboBox<String>();
	private JTextField valorGen = new JTextField();
	private JButton butConclui = new JButton();
	private JButton butEditar = new JButton("Editar");
	private JButton butVoltar = new JButton("Voltar");
	private JButton butExcluir = new JButton("Excluir");
	private ButtonGroup radioGroup;
	private String[] novoDado = new String[10];
	private String[] valueCL = {"Livre", "10", "12", "14", "16", "18"};
	private int posicao;
	private int opcao;
	private static ImageIcon background = new ImageIcon("imgs/KinoDerToten.png");
	private static ImageIcon icone = new ImageIcon("imgs/icon.png");
	private static JLabel labelBackground = new JLabel();
	Font text = new Font("SansSerif", Font.PLAIN, 18);
	Font but = new Font("SansSerif", Font.BOLD ,16);
	private static ControleDados dados;
	
	/**
	 * O m�todo <b>adicionaFilme</b> serve tanto para adicionar um filme novo ao sistema quanto para editar um j� existente.
	 * Aqui tem v�rios compontentes do JSwing para o usu�rio entrar com dados.
	 * @param op -> indica qual caminho tomar (edi��o de existente ou adi��o de novo)
	 * @param d -> recebe instancia de ControleDados
	 */
	public void adicionaFilme(int op, ControleDados d) {
		dados = d;
		opcao = op;
		f = new JFrame("KDT - Adi��o de filme");
		
		preencheComboBox();
		//System.out.println(posicao);
		//System.out.println(dados.getFilmes()[posicao].getClassifIndicativa());
		if (op==1) {
			butConclui.setText("Adicionar");
		}
		
		if (op==2) {
			labelTitle.setText("T�tulo:");
			labelDataLanc.setText("Lancamento:");
			labelDuracao.setText("Duara��o:");
			labelLing.setText("Linguagem:");
			labelClassInd.setText("Classifica��o Indicativa:");
			labelGen.setText("G�nero:");
			
			valorTitle.setText(dados.getFilmes()[posicao].getTitulo());
			valorDataLanc.setText(dados.getFilmes()[posicao].getDataLancamento());	
			valorDuracao.setText(String.valueOf(dados.getFilmes()[posicao].getDuracao()));
			if(d.getFilmes()[posicao].getLinguagem()=="Dublado") lingDub.setSelected(true);
			if(d.getFilmes()[posicao].getLinguagem()=="Legendado") lingLeg.setSelected(true);
			valorClassInd.setSelectedIndex(dados.getFilmes()[posicao].getClassifIndicativa());
			valorGen.setText(dados.getFilmes()[posicao].getGenero());
			
			butConclui.setText("Salvar");			
		}
			f.setVisible(true);
			f.setLayout(null);
			f.setBounds(550, 250, 500, 350);
			f.setResizable(false);
			f.setIconImage(icone.getImage());
			
			f.add(labelTitle);
			f.add(labelDataLanc);
			f.add(labelDuracao);
			f.add(labelLing);
			f.add(labelClassInd);
			f.add(labelGen);
					
			labelTitle.setBounds(85, 10, 220, 30);
			labelDataLanc.setBounds(60, 48, 220, 30);	
			labelDuracao.setBounds(70, 86, 220, 30);
			labelLing.setBounds(65, 124, 220, 30);
			labelClassInd.setBounds(15, 162, 220, 30);
			labelGen.setBounds(75, 200, 220, 30);
			
			labelTitle.setFont(text);
			labelDataLanc.setFont(text);
			labelDuracao.setFont(text);
			labelLing.setFont(text);
			labelClassInd.setFont(text);
			labelGen.setFont(text);
			
			f.add(valorTitle);
			f.add(valorDataLanc);
			f.add(valorDuracao);
			f.add(lingDub);
			f.add(lingLeg);
			f.add(valorClassInd);
			f.add(valorGen);
			valorTitle.setBounds(220, 10, 250, 30);
			valorDataLanc.setBounds(220, 48, 250, 30);		
			valorDuracao.setBounds(220, 86, 250, 30);
			lingLeg.setBounds(220, 124, 100, 30);
			lingDub.setBounds(320, 124, 250, 30);
			valorClassInd.setBounds(220, 162, 250, 30);
			valorGen.setBounds(220, 200, 250, 30);
			
			radioGroup = new ButtonGroup();
			radioGroup.add(lingDub);
			radioGroup.add(lingLeg);
			
			
			f.add(butVoltar);
			f.add(butConclui);
			butConclui.setBounds(200, 250, 120, 40);
			butVoltar.setText("Cancelar");
			butVoltar.setBounds(350, 250, 120, 40);

			butConclui.setFont(but);
			butVoltar.setFont(but);
			
			butConclui.addActionListener(this);
			butVoltar.addActionListener(this);
	}

	/**
	 * O m�todo <b>exibeFilme</b> � um JFrame com JLabels que mostram os detalhes de um Filme j� cadastrado.
	 * Caso o usu�rio queira editar algum desses detalhes, h� uma opc�o de editar, que leva o usu�rio ao m�todo <b>adicionaFilme</b>
	 * @param d. Recebe instancia do ControleDeDados
	 * @param pos -> Recebe Posicao do index da JList
	 */
	public void exibeFilme(ControleDados d, int pos) {
		dados = d;
		posicao = pos;
		
		f = new JFrame("KDT - Detalhes de filme");
		f.setVisible(true);
		f.setLayout(null);
		f.setBounds(550,250, 500, 350);
		f.setResizable(false);
		f.setIconImage(icone.getImage());
		
		f.add(labelTitle);
		f.add(labelDataLanc);
		f.add(labelDuracao);
		f.add(labelLing);
		f.add(labelClassInd);
		f.add(labelGen);
		f.add(butEditar);
		f.add(butVoltar);
		f.add(butExcluir);
				
		labelTitle.setBounds(180, 10, 400, 30);
		labelDataLanc.setBounds(130, 48, 400, 30);	
		labelDuracao.setBounds(150, 86, 400, 30);
		labelLing.setBounds(140, 124, 400, 30);
		labelClassInd.setBounds(40, 162, 400, 30);
		labelGen.setBounds(165, 200, 400, 30);
		
		labelTitle.setFont(text);
		labelDataLanc.setFont(text);
		labelDuracao.setFont(text);
		labelLing.setFont(text);
		labelClassInd.setFont(text);
		labelGen.setFont(text);
		
		labelTitle.setText(labelTitle.getText()+" "+dados.getFilmes()[pos].getTitulo());
		labelDataLanc.setText(labelDataLanc.getText()+" "+dados.getFilmes()[pos].getDataLancamento());
		labelDuracao.setText(labelDuracao.getText()+" "+dados.getFilmes()[pos].getDuracao());
		labelLing.setText(labelLing.getText()+" "+dados.getFilmes()[pos].getLinguagem());
		labelClassInd.setText(labelClassInd.getText()+" "+dados.getFilmes()[pos].getValueClassInd());
		labelGen.setText(labelGen.getText()+" "+dados.getFilmes()[pos].getGenero());
		
		butEditar.setBounds(200, 250, 100, 40);
		butVoltar.setBounds(350, 250, 100, 40);
		butExcluir.setBounds(80, 250, 100, 40);
		
		butEditar.setFont(but);
		butVoltar.setFont(but);
		butExcluir.setFont(but);
		butExcluir.setBackground(Color.RED);
		
		butEditar.addActionListener(this);
		butVoltar.addActionListener(this);
		butExcluir.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src==butConclui) {
			try {
				boolean res;

				if(valorTitle.getText().isEmpty()) {
					res = false;
				} else {
					if (opcao==1) {
						novoDado[0] = Integer.toString(dados.getQtdFilmes());
					}
					if (opcao==2) {
						novoDado[0] = Integer.toString(posicao);
					}
					novoDado[1] = valorTitle.getText();
					novoDado[2] = valorDataLanc.getText();
					novoDado[3] = valorDuracao.getText();
					if(lingDub.isSelected()) {
						novoDado[4] = "Dublado";
					} else if (lingLeg.isSelected()) {
						novoDado[4] = "Legendado";
					}
					novoDado[5] = retornaValueComboBox();
					novoDado[6] = valorGen.getText();
					
					res = dados.inserirEditarFilme(novoDado);
					f.dispose();
				}
			
				if (res) {
					mensagemSucesso();
				} else {
					mensagemErro();
				}
			
			} catch (NullPointerException exc1) {
				mensagemErro();
			}
			
		}
		
		if (src==butVoltar) {
			f.dispose();
		}
		
		if (src==butEditar) {
			f.dispose();
			adicionaFilme(2, dados);	
		}
		
		if(src==butExcluir){
			boolean res = false;
			
			res = dados.removerFilme(posicao);
			if(res) mensagemSucessoRemove();
			else mensagemErroRemove();
			f.dispose();
		}
	}

	/**
	 * Esse m�todo tem como �nico intuito preencher uma ComboBox para o m�todo <b>adicionaFilme</b>
	 */
	public void preencheComboBox() {
		for (int i=0; i<6; i++) {
			valorClassInd.addItem(valueCL[i]);
		}
	}
	
	/**
	 * O m�todo <b>retornaValueComboBox</b> serve para transformar o valor recebido da combobox em uma String, e envi�-lo para o m�todo <b>inserirEditarFilme</b>.
	 * @return string dependendo do valor selecionado na combobox
	 */
	public String retornaValueComboBox() {
		String retorna="0";
		//if (valorClassInd.getSelectedIndex() == 0) retorna = 0;
		if (valorClassInd.getSelectedIndex() == 1) retorna = "1";
		if (valorClassInd.getSelectedIndex() == 2) retorna = "2";
		if (valorClassInd.getSelectedIndex() == 3) retorna = "3";
		if (valorClassInd.getSelectedIndex() == 4) retorna = "4";
		if (valorClassInd.getSelectedIndex() == 5) retorna = "5";
		return retorna;
	}
	
	/**
	 * Passa ao usu�rio um feedback positivo
	 */
	public void mensagemSucesso() {
		if (opcao==1) {
			JOptionPane.showMessageDialog(null, "SUCESSO\nFilme adicionado!", null, JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "SUCESSO\nFilme atualizado!", null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Passa ao usu�rio um feedback negativo
	 */
	public void mensagemErro() {
		JOptionPane.showMessageDialog(null, "ERRO\nCertifique-se de:\n-Preencher todos os Campos.\n-O valor da Dura��o deve ser um n�mero.", null, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Passa ao usu�rio um feedback positivo
	 */
	public void mensagemSucessoRemove() {
		JOptionPane.showMessageDialog(null, "Filme removido com sucesso.", null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Passa ao usu�rio um feedback negativo
	 */
	public void mensagemErroRemove() {
		JOptionPane.showMessageDialog(null, "ERRO\nO Filme n�o pode ser removido pois est� cadastrado em alguma(s) sess�o\nEdite essa sess�o primeiro", null, JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	/*
	public static void mudaFonte (Component component, Font font){
	    component.setFont (font);
	    
	    if (component instanceof Container) {
	        for ( Component child : ((Container) component).getComponents()) {
	            mudaFonte(child, font);
	        }
	    }
	}
	*/
}