package br.com.dbconnect;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * <I><B>Nota:</B> Interface para métodos utilizados no Banco de Dados.
 */
public interface IDAO {

	/**
	 * Função para salvar no MongoDB que recebe um objeto como parâmetro.
	 * @param classe Objeto de alguma classe.
	 * @return boolean, true para sucesso ao salvar e false para alguma falha.
	 */
	boolean save(Object classe);
	
	/**
	 * Função para salvar no MongoDB que recebe um json como parâmetro.
	 * @param json String com padrão json.
	 * @return boolean, true para sucesso ao salvar e false para alguma falha.
	 */
	boolean save(String json);
	
	/**
	 * Função para atualizar um registro no MongoDB que recebe um objeto do tipo BasicDBObject.
	 * @param query json para busca de um registro no banco.
	 * @param update json que substituirá o registro encontrado.
	 * @return boolean, true para sucesso ao atualizar e false para alguma falha.
	 */
	boolean update(BasicDBObject query, BasicDBObject update);
	
	/**
	 * Função para atualizar um registro no MongoDB que recebe um json.
	 * @param query json para busca de um registro no banco.
	 * @param update json que substituirá o registro encontrado.
	 * @return boolean, true para sucesso ao atualizar e false para alguma falha.
	 */
	boolean update(String query, String update);

	/**
	 * Função para excluir um registro no MongoDB que recebe um objeto como parâmetro. 
	 * @param classe Objeto de alguma classe.
	 * @return boolean, true para sucesso ao excluir e false para alguma falha.
	 */
	boolean delete(Object classe);

	/**
	 * Função para excluir um registro no MongoDB que recebe um json como parâmetro.  
	 * @param json String com padrão json.
	 * @return boolean, true para sucesso ao excluir e false para alguma falha.
	 */
	boolean delete(String json);

	/**
	 * Função para localizar apenas um registro que recebe uma classe como parâmetro.
	 * @param classe Objeto de alguma classe.
	 * @return Objeto do tipo DBObject.
	 */
	DBObject findOne(Object classe);

	/**
	 * Função para localizar apenas um registro que recebe um json como parâmetro.
	 * @param json String com padrão json.
	 * @return Objeto do tipo DBObject.
	 */
	DBObject findOne(String json);

	/**
	 * Função para localizar todos os registros de uma tabela.
	 * @return Lista de DBObject.
	 */
	List<DBObject> findAll();

	List<DBObject> findKeyValue(Object keyValue);

	List<DBObject> findKeyValue(String keyValue);

	/**
	 * Função para localizar registro(s).
	 * @param limit Quantidade de registro para o retorno.
	 * @param order true para pegar os registros na tabela do MongoDB de forma Ascendente e false para Descendente.
	 * @return Lista que contém dados do tipo json.
	 */
	List<Object> getRegister(int limit, boolean order);

	/**
	 * Função para localizar registro com o mesmo padrão json com parâmetro BasicDBObject.
	 * @param register Objeto do tipo BasicDBObject para busca no banco.
	 * @return boolean, true para sucesso ao encontrar o registro e false para alguma falha.
	 */
	boolean getEspecificRegister(BasicDBObject register);

	/**
	 * Função para localizar registro com o mesmo padrão json com parâmetro String.
	 * @param register String com padrão json para busca no banco.
	 * @return boolean, true para sucesso ao encontrar o registro e false para alguma falha.
	 */
	boolean getEspecificRegister(String register);
}
