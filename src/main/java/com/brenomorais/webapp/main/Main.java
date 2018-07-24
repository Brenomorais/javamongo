package com.brenomorais.webapp.main;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Main {
	
	public static void main(String[] args) {

		// Test conection app java with database mongodb base
		
		MongoClient client = new MongoClient();
		MongoDatabase dataBase = client.getDatabase("test");
		MongoCollection<Document> alunos = dataBase.getCollection("alunos");
		Document aluno = alunos.find().first();
		
		System.out.println(">> "+aluno);
		
		//Insert new register collection at aluno
		
		@SuppressWarnings("deprecation")
		Document newAluno = new Document("nome","Antonio")
				.append("data_nascimento", new Date(2003, 10,10))
				.append("curso", new Document("nome", "Historia"))
				.append("notas", Arrays.asList(10,9,8))
				.append("habilidades", Arrays.asList(new Document()
						.append("nome", "inglês")
						.append("nivel", "Básico"),
						new Document()
						.append("nome", "Espanhol")
						.append("nivel", "basico")));
		
		alunos.insertOne(newAluno);				
	
		//Update in register of one aluno of collection		
		alunos.updateOne(Filters.eq("nome","Breno"), new Document("$set", new Document("nome","Breno Lopes")));
		
		client.close();
		
	}

}
