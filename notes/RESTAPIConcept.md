#### RestAPI (Jersey, Spring)
where Data come from
access Data from service provider 
Servlet give you data, servlet could return XML/JSON
Object, send state of object as XML/JSON to client
rest => Representational State Transfer, not sending an object but the state of the object
http://abc.com/questions/java more sense than http://abc.com/question?subject=java (it looks more like a resource)
Client not know what is happening => 
#### use CRUD to work RestAPI
C=>Create =>POST
R=>Read => GET
U=>Update => PUT
D=>Delete => DELETE


#### Jersey 
web.xml (define url pattern /webapi/*)
at Class.java , @Path("myresource") outside class
```
inside the class
    @GET // so its get data
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
```
