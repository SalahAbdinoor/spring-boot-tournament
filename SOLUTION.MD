# Solution:

## Run Solution

1. Run Application
2. Application loads 4 players, 3 tournaments and connects all the players to 2 different tournaments
3. Follow Instructions under "POSTMAN"

## POSTMAN

As a part of the task, Postman was suggested as an API-tester. So as a part of my assignment I have set up an API-test using postman.

To run Postman with preloaded values.

1. Import "postman_requests.json" to postman:

![How to Import API](https://assets.postman.com/postman-docs/import-multiple-apis.gif)

2. Run collection

![How to run collection](https://assets.postman.com/postman-docs/collection-runner-for-v8.gif)

> DISCLAIMER: Once the postman collection is run, some data will be deleted/put. To run as intended reload project 

## Scope

To limit the scope so that the assignment doesn't get to long-winded, I have:

- Decided to keep the business-logic inside the controller-classes (not good practice). instead of the service classes.
- Limited the validation to validating the currency on Tournament.java
- Let Hibernate set up the database (Normally I would have se my own tables/triggers/stored procedures/constraints/...)
- Decided to go with only a "putModel" for my "Put/Patch-Mapping", rather than going with a more traditional: saveModel/partialUpdateModel

## Resolved Issues

### Problem: Cascading
	When deleting a player/tournament that is connected to an exercise -> causes: Referential integrity constraint violation.
### Reason: Cascading
	This issue is due to constraint not having been set properly. for instance "@ManyToOne(cascade = CascadeType.ALL)"
### Possible Work-around: Cascading
	In exercise table: foreign key (Player/Tournament-ID) references Player/Tournament-(ID) on delete cascade
### Actual Work-around: Cascading
	When deleting player/tournament that is connected to an exercise: First delete exercise -> Second delete player/tournament (Not a scalable solution)


## Reflection

### General
Overall as someone who has developed in Java for just over a year, I would say that this assignment was beginner-friendly and the scope was reasonable.
I learnt a lot of new awesome techniques and plan on expanding on those these coming years!

### What went well?
- The JPA/Hibernate part of the solution went exceedingly well. 
Usually the "mapping"/"joining" columns take a lot more time to do using only hibernate
but since the only relation-mapping is in the Model: Exercise. Mapping wasn't that much of an issue

### What didn't go so well?
- Seeing as I'm in several technical-interview currently I only had so much time to spend on this particular assignment.

### What will I do the next time?
- Planning, planning & PLANNING! If I had started out with a more SCRUM like method with tickets, ER-Diagram and so on, I would have been able to do more in less time!

### How to further develop this app?

- Move the business logic from the controller-classes and into the service-classes. 
- Add business-logic such as showing what players are attending the same tournaments (SELECT * FROM EXERCISE WHERE TOURNAMENT_ID=ID)
- Set up the database using SQL.
- Create a couple of fun scenarios that could be presented in the console. (Like to players changing spots in tournaments)
- convert putModel in each controller to saveModel/partialUpdateModel
- Change the "getModels" in the controllers to something close to this:
- Add Spring security and handle requests using JWT
- Exception handling -> throw-early-catch-late.
- Handle Enum outside of Tournaments.Model in a separate JSON and read from there so that the frontend also could read the same data!
- Better validation for input-parameters.
- More Integration-tests using Mockito
- More Unit-tests using Jupiter

-- TODO:
13. gå igenom kommentarer i alla filer
14. skapa unit/integrations-tester