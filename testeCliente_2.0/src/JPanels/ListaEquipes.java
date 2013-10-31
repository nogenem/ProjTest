package JPanels;

import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import PP_Observer.Notificador;
import PP_Observer.Notificavel;

public class ListaEquipes extends JPanel implements ActionListener, INossoPanel, Notificador {

	private Notificavel notificado;
	private PrintWriter escritor;
	private INossoPanel next; //Proximo conteudo
	
	private String newTeamName;//nome da nova equipe a ser adicionada
	private String removedTeamName;//nome da equipe que sera removida
	
	private JList<String> list;
	private DefaultListModel<String> listaEquipes = new DefaultListModel<>();  
	
	public ListaEquipes(PrintWriter escritor){
		this.escritor = escritor;
		
		setLayout(null);
		setSize(267+5, 376+30);
		
		JLabel lblListaDeEquipes = new JLabel("Lista de equipes:");
		lblListaDeEquipes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaDeEquipes.setBounds(10, 11, 128, 24);
		add(lblListaDeEquipes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 43, 226, 184);
		add(scrollPane);
		
		list = new JList<>();
		list.setModel(listaEquipes);  
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION); 
		scrollPane.setViewportView(list);
		
		JButton btnListMembers = new JButton("Listar Membros");
		btnListMembers.setMargin(new Insets(0, 0, 0, 0));
		btnListMembers.setBounds(20, 238, 104, 23);
		btnListMembers.addActionListener(this);
		add(btnListMembers);
		
		JButton btnListProjs = new JButton("Listar Projetos");
		btnListProjs.setMargin(new Insets(0, 0, 0, 0));
		btnListProjs.setBounds(142, 238, 104, 23);
		btnListProjs.addActionListener(this);
		add(btnListProjs);
		
		JButton btnListarArquivos = new JButton("Listar Arquivos");
		btnListarArquivos.setMargin(new Insets(0, 0, 0, 0));
		btnListarArquivos.setBounds(20, 272, 104, 23);
		btnListarArquivos.addActionListener(this);
		add(btnListarArquivos);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setMargin(new Insets(0, 0, 0, 0));
		btnVoltar.setBounds(142, 272, 104, 23);
		btnVoltar.addActionListener(this);
		add(btnVoltar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 302, 247, 59);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblPainelDeAdm = new JLabel("Painel de ADM:");
		lblPainelDeAdm.setBounds(10, 0, 85, 14);
		panel.add(lblPainelDeAdm);
		
		JButton btnAddEquipe = new JButton("Add Equipe");
		btnAddEquipe.setMargin(new Insets(0, 0, 0, 0));
		btnAddEquipe.setBounds(6, 25, 114, 23);
		btnAddEquipe.addActionListener(this);
		panel.add(btnAddEquipe);
		
		JButton btnRemoveEquipe = new JButton("Remove Equipe");
		btnRemoveEquipe.setMargin(new Insets(0, 0, 0, 0));
		btnRemoveEquipe.setBounds(127, 25, 110, 23);
		btnRemoveEquipe.addActionListener(this);
		panel.add(btnRemoveEquipe);
	}

	@Override
	public INossoPanel getNext() {
		return next;
	}

	@Override
	public void parsePacket(JSONObject packet) {
		if(packet.has("lista")){ //lista de equipes vinda do servidor
			JSONArray array = packet.getJSONArray("lista");
			if(array.length() == 0)
				return;
	
			for(int i = 0; i<array.length(); i++)
				listaEquipes.addElement(array.get(i).toString());

			list.setSelectedIndex(0);
		}else if(packet.has("OK")){ //confirmacao q uma equipe foi adicionada/removida com sucesso
			String tmp = packet.getString("OK");
			
			if(tmp.contains("Equipe adicionada")){//ser for adicionado nova equipe, add nova label na lista
				listaEquipes.addElement(newTeamName);
				list.setSelectedIndex(list.getLastVisibleIndex());
			}else if(tmp.contains("Equipe removida")){//ser for removido uma equipe, remove uma label da lista
				listaEquipes.removeElement(removedTeamName);
				list.setSelectedIndex(list.getLastVisibleIndex());
			}
		}
	}

	@Override
	public void setarNotificavel(Notificavel notificavel) {
		this.notificado = notificavel;
	}

	@Override
	public void notificar(JSONObject packet) {
		notificado.serNotificado(packet);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Listar Membros")){
			String equipeName = list.getSelectedValue();
			
			if(equipeName == null){
				JOptionPane.showMessageDialog(this, "Selecione uma equipe antes.");
				return;
			}
			
			next = new ListaMembros(escritor, equipeName);
			
			JSONObject packet = new JSONObject();
			
			HashMap<String, String> tmp = new HashMap<>();
			tmp.put("equipe", equipeName);
			
			packet.put("listarMembros", tmp);
			
			escritor.println(packet.toString());
			escritor.flush();
			
		}else if(arg0.getActionCommand().equals("Listar Projetos")){
			String equipeName = list.getSelectedValue();
			
			if(equipeName == null){
				JOptionPane.showMessageDialog(this, "Selecione uma equipe antes.");
				return;
			}
			
			next = new ListaProjs(escritor, equipeName);
			
			JSONObject packet = new JSONObject();
			
			HashMap<String, String> tmp = new HashMap<>();
			tmp.put("equipe", equipeName);
			
			packet.put("listarProjetos", tmp);
			
			escritor.println(packet.toString());
			escritor.flush();
			
		}else if(arg0.getActionCommand().equals("Listar Arquivos")){
			String equipeName = list.getSelectedValue();
			
			if(equipeName == null){
				JOptionPane.showMessageDialog(this, "Selecione uma equipe antes.");
				return;
			}
			next = new ListaArquivos(escritor, equipeName);
			
			JSONObject packet = new JSONObject();
			
			HashMap<String, String> tmp = new HashMap<>();
			tmp.put("equipe", equipeName);
			
			packet.put("listarArquivos", tmp);
			
			escritor.println(packet.toString());
			escritor.flush();
			
		}else if(arg0.getActionCommand().equals("Voltar")){
			next = new Login(escritor);

			JSONObject packet = new JSONObject();
        	packet.put("desconectar", "sair");

        	escritor.println(packet.toString());
        	escritor.flush();
		}else if(arg0.getActionCommand().equals("Add Equipe")){
			String equipeName = JOptionPane.showInputDialog(this, "Informe o nome da nova equipe:");
			
			if(equipeName == null)
				return;
			else if(equipeName.equals("")){
				JOptionPane.showMessageDialog(this, "Voce deve informar um nome para a nova equipe.");
				return;
			}
			
			newTeamName = equipeName;
			JSONObject packet = new JSONObject();
			
			HashMap<String, String> tmp = new HashMap<>();
			tmp.put("nome", equipeName);
			
			packet.put("addEquipe", tmp);
			
			escritor.println(packet.toString());
			escritor.flush();
		}else if(arg0.getActionCommand().equals("Remove Equipe")){
			String toRemove = list.getSelectedValue();
			
			if(toRemove == null){
				JOptionPane.showMessageDialog(this, "Selecione uma equipe antes.");
				return;
			}
			
			int resposta = JOptionPane.showConfirmDialog(this, "Voce tem certeza que deseja remover a equipe: "+
														toRemove +"?");
			if(resposta == 0){
				removedTeamName = toRemove;
				JSONObject packet = new JSONObject();
				
				HashMap<String, String> tmp = new HashMap<>();
				tmp.put("nome", toRemove);
				
				packet.put("removeEquipe", tmp);
				
				escritor.println(packet.toString());
				escritor.flush();
			}
		}
	}
}
