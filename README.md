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