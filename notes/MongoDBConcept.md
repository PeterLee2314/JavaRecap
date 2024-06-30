### Mongo DB

#### MongoDB Compass (Access on local application)

#### Atlas Search (Search Feature)
Pipeline , a sequence of filter (aka stage)
all document is (Indexing)
On Collections -> aggregation -> stage 
query (something you want to search)
path (where)
```
{
  index: 'default',
  text: {
    query: 'java',
    path: 'techs' // OR path: ['techs','desc','profile']
  }
}

$sort

{
  exp: 1 // 1 is asc -1 is desc
}
```

#### Replica set
extra working database for back up

#### Client side field encryption
Field Encrpytion, form the server side send to database (it will get encrypted)
encrypt some fields