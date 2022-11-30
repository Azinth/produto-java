package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Contact;

public class ContactDialog extends JDialog implements ActionListener {

	private Contact contact;

	private JButton btnAction, btnCancel;

	private JTextField productName, categoria, fabricacao, validade;

	public ContactDialog(JFrame owner, Contact contact) {
		super(owner, true);
		setLocationRelativeTo(owner);

		productName = new JTextField(15);
		categoria = new JTextField(15);
		fabricacao = new JTextField(15);
		validade = new JTextField(15);

		if (contact != null) {
			this.contact = contact;
			btnAction = new JButton("Editar");
			setTitle("Editar Produto");

			productName.setText(contact.getProductName());
			categoria.setText(contact.getCategoria());
			fabricacao.setText(contact.getFabricacao());
			validade.setText(contact.getValidade());
		} else {
			this.contact = new Contact();
			btnAction = new JButton("Adicionar");
			setTitle("Adicionar Produto");
		}
		setSize(400, 170);
		setLayout(new FlowLayout());

		JPanel panelFields = new JPanel(new GridLayout(4,2));
		panelFields.add(new JLabel("Nome do Produto:"));
		panelFields.add(productName);
		panelFields.add(new JLabel("Categoria:"));
		panelFields.add(categoria);
		panelFields.add(new JLabel("Fabricação:"));
		panelFields.add(fabricacao);
		panelFields.add(new JLabel("Validade:"));
		panelFields.add(validade);

		JPanel panelButtons = new JPanel(new FlowLayout());
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(this);
		btnAction.addActionListener(this);
		panelButtons.add(btnAction);
		panelButtons.add(btnCancel);

		add(panelFields);
		add("South", panelButtons);

		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public Contact getContact() {
		return contact;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String product1 = productName.getText().trim();
		String categoria1 = categoria.getText().trim();
		String fabricacao1 = fabricacao.getText().trim();
		String validade1 = validade.getText().trim();

		
		if (e.getSource() == btnCancel) {
			contact = null;
		} else {
			if (productName.equals("") || categoria.equals("")) {
				JOptionPane.showMessageDialog(this, "Prencha o nome do propoduto e categoria");
				return;
			}
			
			/*if(!validade.equals("")&& !validade.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
				JOptionPane.showMessageDialog(this, "E-mail inválido");
				return;
			}*/
			
			contact.setProductName(product1);
			contact.setCategoria(categoria1);
			contact.setFabricacao(fabricacao1);
			contact.setValidade(validade1);
		}
		setVisible(false);

	}

}
