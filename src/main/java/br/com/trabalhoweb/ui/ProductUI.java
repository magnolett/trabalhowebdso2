package br.com.trabalhoweb.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.Product;
import br.com.trabalhoweb.entities.User;
import br.com.trabalhoweb.service.ProductServiceImpl;

@Component
public class ProductUI extends JFrame {
	private static final long serialVersionUID = 1632320301077048445L;

	private JLabel jcomp1;
    private JTextField jcomp2;
    private JLabel jcomp3;
    private JButton jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;
    private JLabel jcomp7;
    private JCheckBox jcomp8;
    private JCheckBox jcomp9;
    private JTextArea jcomp10;
    private JButton jcomp11;

	Boolean isNegociable = false;

    @Autowired
    private DecisionUI decisionUI;

    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    private User user;
    
    @PostConstruct
    public void productUI() {

    	jcomp1 = new JLabel ("Título do Anúncio");
        jcomp2 = new JTextField(5);
        jcomp3 = new JLabel ("Descrição:");
        jcomp4 = new JButton ("Cadastrar Produto");
        jcomp5 = new JLabel ("Preço: ");
        jcomp6 = new JTextField(5);
        jcomp7 = new JLabel ("Negociável:");
        jcomp8 = new JCheckBox ("Sim");
        jcomp9 = new JCheckBox ("Não");
        jcomp10 = new JTextArea (5, 5);
        jcomp11 = new JButton ("Carregar Fotos");

        setPreferredSize (new Dimension (529, 410));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (jcomp11);

        jcomp1.setBounds (220, 15, 115, 25);
        jcomp2.setBounds (90, 50, 350, 25);
        jcomp3.setBounds (240, 95, 70, 25);
        jcomp4.setBounds (210, 345, 140, 25);
        jcomp5.setBounds (125, 225, 50, 25);
        jcomp6.setBounds (90, 265, 100, 25);
        jcomp7.setBounds (355, 225, 70, 25);
        jcomp8.setBounds (335, 265, 55, 25);
        jcomp9.setBounds (400, 265, 60, 25);
        jcomp10.setBounds (90, 125, 350, 95);
        jcomp11.setBounds (220, 300, 120, 25);
        
    	jcomp8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jcomp9.isSelected()) {
					jcomp8.setSelected(false);
				}
			}
		});
    	jcomp9.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if(jcomp8.isSelected()) {
    				jcomp9.setSelected(false);
    			}
    		}
    	});
    	
    	if(jcomp8.isSelected()) {
    		isNegociable = true;
    	} else if(jcomp8.isSelected()) {
    		isNegociable = false;
    	} else {
    		isNegociable = null;
    	}
    	
    	jcomp4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarProduto(user);
			}
		});
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
    }
    
    public void buildFrameIfViewing(Product product) {
    	
    	jcomp2.setText(product.getName());
    	jcomp6.setText(product.getPrice().toString());
    	
    	boolean negociable = product.isNegociable();
    	if(negociable) {
    		jcomp8.setSelected(true);
    	} else if (!negociable) {
    		jcomp9.setSelected(true);
    	} else {
    		jcomp8.setSelected(false);
    		jcomp9.setSelected(false);
    	}
    	
    	jcomp10.setText(product.getDescription());
    	
    	jcomp2.setEditable(false);
    	jcomp6.setEditable(false);
    	jcomp8.setEnabled(false);
    	jcomp9.setEnabled(false);
    	jcomp10.setEditable(false);
    	
    	jcomp11.setVisible(false);
    	jcomp4.setVisible(false);
    	
    	JButton voltar = new JButton("Voltar");
    	JButton contato = new JButton("Entrar em contato");

    	voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				decisionUI.criaTela(product.getUser());
			}
		});
    	
    	contato.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "O contato do vendedor é: " + product.getUser().getCellphone(), "Contato", JOptionPane.INFORMATION_MESSAGE);
			}
		});
    	
    	add(voltar);
    	add(contato);
    	//    	this.buildFrame(product.getUser());
    }
    
    public void cadastrarProduto(User user) {
    	final Product product = new Product();

    	Double price = null;
    	
    	product.setDescription(jcomp10.getText());
    	product.setName(jcomp2.getText());
    	product.setNegociable(true);
    	
    	if(jcomp6.getText().replaceAll("\\D", "").length() == 0) {
    		JOptionPane.showMessageDialog(null, "Campo de valor em branco e/ou com caracteres inválidos!", "Apenas valores nesse campo", JOptionPane.ERROR_MESSAGE);
    	} else {
    		price = Double.valueOf(jcomp6.getText().replaceAll("\\D", ""));
    	}
    	
    	product.setPrice(price);
    	product.setUser(user);
    	
    	productServiceImpl.save(product);
    	JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Criado!", JOptionPane.INFORMATION_MESSAGE);
    	
    	this.setVisible(false);
    	decisionUI.criaTela(user);
    }

//    public void buildFrame(User user) {
//    	
//    	JFrame frame = new JFrame ("Cadastrar Produtos");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add (productUI());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible (true);
//    }
    
    public void criaTela(User user) {
    	this.user = user;
    	setVisible(true);
    }
}
