# json-to-sql

A Clojure library designed to convert JSON to SQL queries and statements. It can convert JSON to SELECT, INSERT, UPDATE and DELETE SQL commands.
This is a POC (Proof of Concept) as a part of some ORM framework written in Clojure.

## Requirements

Cloruje 1.8.0

## Instalation

To include library add following to your ```dependencies``` ```[json-to-sql "0.1.0]```

## Usage

JSON must have top level object with 2 properties. First property represents the name of the database table. Depend on which SQL command you want to get that propery can be empty object or object with properties and its values. Second property should be named "conditions" and to represents an array of objects with following properties: "column", "operation" and "value".

### JSON Example

```
{
  "Users": {
    "name": "Joe Doe",
    "address": "Fake street 123",
    "date_of_birth": "01.01.1900",
    "is_admin": true,
    "card_no": 123
  },
  "conditions": [
    {
      "column" : "name",
      "operation": "like",
      "value": "Fake"
    },
    {
      "column" : "card_no",
      "operation": "eq",
      "value": 123
    }
  ]
}
```

>If you want to try out this library in ```json-to-sql.core``` namespace there is 4 defined path to JSON files expamles:
> * select-json-file
> * insert-json-file
> * update-json-file
> * delete-json-file

All JSON files are in "Resources" folder.

### SELECT query

```json->select``` is a method to convert JSON to SQL **SELECT** query. It take only one argument and that's to JSON file.

#### SELECT query example

To get **SELECT** SQL query you can call ```(json->select select-json-file)```.

**SELECT**: ```SELECT name, address, date_of_birth, is_admin, card_no FROM Users WHERE name LIKE 'Fake' AND card_no = 123```
**JSON**:
```
{
  "Users": {
    "name": "",
    "address": "",
    "date_of_birth": "",
    "is_admin": "",
    "card_no": ""
  },
  "conditions": [
    {
      "column" : "name",
      "operation": "like",
      "value": "Fake"
    },
    {
      "column" : "card_no",
      "operation": "eq",
      "value": 123
    }
  ]
}
```

**SELECT**: ```SELECT * FROM Users WHERE name LIKE 'Fake' AND card_no = 123```  
**JSON**:
```
{
  "Users": {
    "*": ""
  },
  "conditions": [
    {
      "column" : "name",
      "operation": "like",
      "value": "Fake"
    },
    {
      "column" : "card_no",
      "operation": "eq",
      "value": 123
    }
  ]
}
```

#### INSERT statement example

To get **INSERT** SQL statement you can call ```(json->insert insert-json-file)```.

**INSERT**: ```INSERT INTO Users (name, address, date_of_birth, is_admin, card_no) VALUES ('Joe Doe', 'Fake street 123', '01.01.1900', 1, 123)```  
**JSON**:
```
{
  "Users": {
    "name": "Joe Doe",
    "address": "Fake street 123",
    "date_of_birth": "01.01.1900",
    "is_admin": true,
    "card_no": 123
  }
}
```

#### UPDATE statement example

To get **UPDATE** SQL statement you can call ```(json->update update-json-file)```.

**UPDATE**: ```UPDATE Users SET name = 'Joe Doe', address = 'Fake street 123', date_of_birth = '01.01.1900', is_admin = 1, card_no = 123 WHERE name LIKE 'Fake' AND card_no = 123```  
**JSON**:
```
{
  "Users": {
    "name": "Joe Doe",
    "address": "Fake street 123",
    "date_of_birth": "01.01.1900",
    "is_admin": true,
    "card_no": 123
  },
  "conditions": [
    {
      "column" : "name",
      "operation": "like",
      "value": "Fake"
    },
    {
      "column" : "card_no",
      "operation": "eq",
      "value": 123
    }
  ]
}
```

#### DELETE statement example

To get **DELETE** SQL statement you can call ```(json->delete delete-json-file)```.

**DELETE**: ```DELETE FROM Users WHERE name LIKE 'Fake' AND card_no = 123```  
**JSON**:
```
{
  "Users": {},
  "conditions": [
    {
      "column" : "name",
      "operation": "like",
      "value": "Fake"
    },
    {
      "column" : "card_no",
      "operation": "eq",
      "value": 123
    }
  ]
}
```