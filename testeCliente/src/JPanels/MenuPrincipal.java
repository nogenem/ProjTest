package JPanels;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONObject;
import PP_Observer.Notificador;
import PP_Observer.Notificavel;

public class MenuPrincipal extends JPanel implements ActionListener, INossoPanel, Notificador {

	private Notificavel notificado;
	private PrintWriter escritor;
	private INossoPanel next; //Proximo conteudo

	public MenuPrincipal(PrintWriter escritor){
		this.escritor = escritor;
		this.next = null; //O next sera mudado sempre, dependendo de qual botao for clicado.

		setLayout(null);
		setSize(414+10, 305+30); //gambiarra hehe

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMenu.setBounds(10, 11, 46, 14);
		add(lblMenu);

		JLabel lblFunesNormais = new JLabel("Fun\u00E7\u00F5es normais:");
		lblFunesNormais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFunesNormais.setBounds(10, 68, 124, 14);
		add(lblFunesNormais);

		JLabel lblFunesDeAdm = new JLabel("Fun\u00E7\u00F5es de ADM:");
		lblFunesDeAdm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFunesDeAdm.setBounds(10, 174, 107, 14);
		add(lblFunesDeAdm);

		JButton btnListarTarefas = new JButton("Listar Tarefas");
		btnListarTarefas.setMargin(new Insets(0, 0, 0, 0));
		btnListarTarefas.setBounds(10, 93, 124, 23);
		btnListarTarefas.addActionListener(this);
		add(btnListarTarefas);

		JButton btnListarProjetos = new JButton("Listar Projetos");
		btnListarProjetos.setMargin(new Insets(0, 0, 0, 0));
		btnListarProjetos.setBounds(10, 127, 124, 23);
		btnListarProjetos.addActionListener(this);
		add(btnListarProjetos);

		JButton btnListarArquivos = new JButton("Listar Arquivos");
		btnListarArquivos.setMargin(new Insets(0, 0, 0, 0));
		btnListarArquivos.setBounds(144, 93, 124, 23);
		btnListarArquivos.addActionListener(this);
		add(btnListarArquivos);

		JButton btnAdicionarArquivo = new JButton("Adicionar Arquivo");
		btnAdicionarArquivo.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionarArquivo.setBounds(144, 127, 124, 23);
		btnAdicionarArquivo.addActionListener(this);
		add(btnAdicionarArquivo);

		JButton btnAdicionarTarefa = new JButton("Adicionar Tarefa");
		btnAdicionarTarefa.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionarTarefa.setBounds(144, 267, 124, 23);
		btnAdicionarTarefa.addActionListener(this);
		add(btnAdicionarTarefa);

		JButton btnAdicionarProjeto = new JButton("Adicionar Projeto");
		btnAdicionarProjeto.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionarProjeto.setBounds(10, 199, 124, 23);
		btnAdicionarProjeto.addActionListener(this);
		add(btnAdicionarProjeto);

		JButton btnAdicionarEquipe = new JButton("Adicionar Equipe");
		btnAdicionarEquipe.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionarEquipe.setBounds(144, 199, 124, 23);
		btnAdicionarEquipe.addActionListener(this);
		add(btnAdicionarEquipe);

		JButton btnAdicionarMembro = new JButton("Adicionar Membro");
		btnAdicionarMembro.setMargin(new Insets(0, 0, 0, 0));
		btnAdicionarMembro.setBounds(280, 199, 124, 23);
		btnAdicionarMembro.addActionListener(this);
		add(btnAdicionarMembro);

		JButton btnRemoverMembro = new JButton("Remover Membro");
		btnRemoverMembro.setMargin(new Insets(0, 0, 0, 0));
		btnRemoverMembro.setBounds(280, 233, 124, 23);
		btnRemoverMembro.addActionListener(this);
		add(btnRemoverMembro);

		JButton btnRemoverProjeto = new JButton("Remover Projeto");
		btnRemoverProjeto.setMargin(new Insets(0, 0, 0, 0));
		btnRemoverProjeto.setBounds(10, 233, 124, 23);
		btnRemoverProjeto.addActionListener(this);
		add(btnRemoverProjeto);

		JButton btnRemoverEquipe = new JButton("Remover Equipe");
		btnRemoverEquipe.setMargin(new Insets(0, 0, 0, 0));
		btnRemoverEquipe.setBounds(144, 233, 124, 23);
		btnRemoverEquipe.addActionListener(this);
		add(btnRemoverEquipe);

		JButton btnVoltar = new JButton("Sair");
		btnVoltar.setMargin(new Insets(0, 0, 0, 0));
		btnVoltar.setBounds(315, 9, 89, 23);
		btnVoltar.addActionListener(this);
		add(btnVoltar);
	}

	@Override
	public INossoPanel getNext() {
		return next;
	}

	@Override
	public void parsePacket(JSONObject packet) {
		//nao sera implementado nd aki
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Sair")){
			next = new Login(escritor);

			JSONObject packet = new JSONObject();
        	packet.put("desconectar", "sair");

        	escritor.println(packet.toString());
        	escritor.flush();
		}else if(arg0.getActionCommand().equals("Adicionar Projeto")){
			next = new EscolheEquipe(escritor, new AdicionarProj(escritor));//antes eh preciso escolher a equipe
																			//eh preciso passar qual vai ser o proximo panel
			JSONObject packet = new JSONObject();
        	packet.put("listarEquipes", "");//pede a lista de equipes pro servidor

        	escritor.println(packet.toString());
        	escritor.flush();
		}else if(arg0.getActionCommand().equals("Adicionar Equipe")){
			next = new AdicionarEquipe(escritor);

			notificar(null);
		}
	}

	@Override
	public void setarNotificavel(Notificavel notificavel) {
		this.notificado = notificavel;
	}

	@Override
	public void notificar(JSONObject packet) { //Notifica a GUI para mudar para o proximo conteudo
		notificado.serNotificado(packet);
	}
}
