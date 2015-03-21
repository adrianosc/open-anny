package br.com.dbconnect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * Classe de estrutura de Banco de Dados.
 * @see http://www.mballem.com/
 */
public class EntityDao implements IDAO {

	private Class<?> persistentClass;
	private DBCollection dbCollection;
	private static Utils utils = new Utils();

	public EntityDao(Class<?> persistentClass) {
		this.persistentClass = persistentClass;
		if(AbreConexao())
		this.dbCollection = Connect.getInstance().getDB().getCollection(persistentClass.getSimpleName());
		else
			System.out.println("Falha ao Conectar com o Banco, verifique as configurações no arquivo connect.properties");
	}
	
	/**
	 * Função para criar Arquivo connect.properties.
	 */
	private static void createProperties(){		
		try {
			if(!new File("connect.properties").exists())
			{
			BufferedWriter bw = new BufferedWriter(new FileWriter("connect.properties"));
			bw.write("HOST=\n");
			bw.write("PORT=\n");
			bw.write("DB_NAME=\n");
			bw.write("DB_LOGIN=\n");
			bw.write("DB_PASSWORD=\n");
			bw.flush();
			bw.close();
			}
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			utils.Log(EntityDao.class).error(ex);
		}
	}

	
	/**
	 * Função para autenticação do Banco de Dados.
	 * @return true para sucesso na autenticação e false para falha.
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	private static boolean AbreConexao() {
		try {
			createProperties();
			Properties props = new Properties();
			props.load(new FileInputStream("connect.properties"));
			if (new Connect().getInstance().getDB().authenticate(props.getProperty("DB_LOGIN"), props.getProperty("DB_PASSWORD").toCharArray()))
				return true;
			return false;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}

	/**
	 * Função para tabela do MongoDB.
	 * @return Retorna um objeto do tipo DBCollection.
	 */
	protected DBCollection getDbCollection() {
		return dbCollection;
	}

	@Override
	public boolean save(Object classe) {
		try {
			dbCollection.save((BasicDBObject) JSON.parse(new Utils().convertToJson(classe)));					
			return true;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}
	@Override
	public boolean update(BasicDBObject query, BasicDBObject update) {
		try {
			BasicDBObject b = new BasicDBObject();
			b.put("$set", update);
			dbCollection.update(query, b);
			return true;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}
	@Override
	public boolean delete(Object classe) {
		try {
			dbCollection.remove((BasicDBObject) JSON.parse(new Utils()
					.convertToJson(classe)));
			return true;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}
	@Override
	public DBObject findOne(Object classe) {

		return dbCollection.findOne((BasicDBObject) JSON.parse(new Utils()
				.convertToJson(classe)));
	}
	@Override
	public List<DBObject> findAll() {
		try {
			List<DBObject> list = new ArrayList<DBObject>();

			DBCursor cursor = dbCollection.find();

			while (cursor.hasNext()) {
				list.add(cursor.next());
			}

			return list;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}
	@Override
	public List<Object> getRegister(int limit, boolean order) {
		try {
			DBCursor cursor = dbCollection.find()
					.sort(new BasicDBObject("_id", order ? 1 : -1))
					.limit(limit);

			List<Object> register = new ArrayList<Object>();

			while (cursor.hasNext()) {
				Object o = new Utils().convertToObject(
						cursor.next().toString(), persistentClass);
				register.add(o);
			}
			return register;
		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return null;
		}
	}

	@Override
	public boolean getEspecificRegister(BasicDBObject register) {
		try {
			if (Connect.getInstance().getDB()
					.collectionExists(persistentClass.getSimpleName())) {
				DBCursor cursor = dbCollection.find(register);
				if (cursor.hasNext()) {
					Object o = new Utils().convertToJson(cursor.next()
							.toString());

					System.out.println(o);
					return true;
				}

				System.out.println("Não Encontrado!!!");
				return false;
			} else {
				System.out.println("Collection Não Existe");
				return false;
			}

		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}

	@Override
	public boolean save(String json) {
		// TODO Auto-generated method stub
		dbCollection.save((DBObject) JSON.parse(json));
		return false;
	}

	@Override
	public boolean update(String query, String update) {
		// TODO Auto-generated method stub
		dbCollection.update((BasicDBObject) JSON.parse(query),
				(BasicDBObject) JSON.parse(update));
		return false;
	}

	@Override
	public boolean delete(String json) {
		// TODO Auto-generated method stub
		dbCollection.remove((BasicDBObject) JSON.parse(json));
		return false;
	}

	@Override
	public boolean getEspecificRegister(String register) {
		// TODO Auto-generated method stub
		try {
			if (Connect.getInstance().getDB()
					.collectionExists(persistentClass.getSimpleName())) {
				DBCursor cursor = dbCollection.find((BasicDBObject) JSON
						.parse(register));
				if (cursor.hasNext()) {
					Object o = new Utils().convertToJson(cursor.next()
							.toString());

					System.out.println(o);
					return true;
				}

				System.out.println("Não Encontrado!!!");
				return false;
			} else {
				System.out.println("Collection Não Existe");
				return false;
			}

		} catch (Exception ex) {
			utils.Log(EntityDao.class).error(ex);
			return false;
		}
	}

	@Override
	public DBObject findOne(String json) {
		// TODO Auto-generated method stub
		return dbCollection.findOne((BasicDBObject) JSON.parse(json));
	}

	@Override
	public List<DBObject> findKeyValue(Object keyValue) {
		// TODO Auto-generated method stub
		List<DBObject> list = new ArrayList<DBObject>();
		return list;
	}

	@Override
	public List<DBObject> findKeyValue(String keyValue) {
		// TODO Auto-generated method stub
		List<DBObject> list = new ArrayList<DBObject>();
		return list;
	}
}
