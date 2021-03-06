package Modelo.Persistencia;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Modelo.Infos.InfoArquivo;

public class ArquivoDao extends Dao {

	public ArquivoDao(ConexaoBanco c, String tabela) {
		super(c, tabela);
	}
	
	public void add(InfoArquivo info, int id_equipe) throws Exception
	{
		HashMap<String, String> dados = new HashMap<>();
		dados.put("TITULO", info.getTitulo());
		dados.put("CONTEUDO", info.getConteudo());
		dados.put("ID_EQUIPE", ""+id_equipe);
		
		this.insert(dados);
	}
	
	public void remove(String titulo, int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.delete(cond);
	}
	
	public void removeAll(int id_equipe) throws Exception //Vai remover todos os arquivos da equipe q veio como parametro
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.delete(cond);
	}
	
	public InfoArquivo view(String titulo, int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.select(cond);
		
		ResultSet result = this.getResultSet();
		
		if(result != null && result.next())
		{
			InfoArquivo info = new InfoArquivo(result.getString("TITULO"), result.getString("CONTEUDO"));
			return info;
		}
		return null;
	}
	
	public void edit(InfoArquivo info, int id_equipe) throws Exception
	{
		HashMap<String, String> dados = new HashMap<>();
		dados.put("TITULO", info.getTitulo());
		dados.put("CONTEUDO", info.getConteudo());
		
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", info.getTitulo());
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.update(dados, cond);
	}
	
	public Set<String> list(int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.select(cond);
		
		ResultSet result = this.getResultSet();
		Set<String> retorno = new HashSet<>();
		
		while(result != null && result.next())
		{
			retorno.add(result.getString("TITULO"));
		}
		return retorno;
	}
	
	public boolean exist(String titulo, int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.select(cond);
		
		ResultSet result = this.getResultSet();
		return result != null && result.next();
	}
}
