package com.learn.mongocontroller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.model.Book;

@RestController
public class MongoController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/mongo/books")
	public Book[] getAllBooks()
	{
         HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity<Book> entity = new HttpEntity<Book>(headers);
         
         return restTemplate.exchange("http://localhost:8080/books/getBook",HttpMethod.GET,entity,Book[].class).getBody();
	}
	
	@RequestMapping(value="/mongo/addBooks",method=RequestMethod.POST)
	public Book addBook(@RequestBody Book book)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(book,headers);
		
		return restTemplate.exchange("http://localhost:8080/books/addBook",HttpMethod.POST,entity,Book.class).getBody();
	}
	@RequestMapping(value="/mongo/updateBooks/{id}",method=RequestMethod.PUT)
	public Book updateBook(@PathVariable("id") String id,@RequestBody Book book)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(book,headers);
		
		return restTemplate.exchange("http://localhost:8080/books/update/"+id,HttpMethod.PUT,entity,Book.class).getBody();
	}
	
	@RequestMapping(value="/mongo/deleteBooks/{id}",method=RequestMethod.DELETE)
	public Book deleteBook(@PathVariable("id")String id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(headers);
		return restTemplate.exchange("http://localhost:8080/books/delete/"+id,HttpMethod.DELETE,entity,Book.class).getBody();
	}
	
}
