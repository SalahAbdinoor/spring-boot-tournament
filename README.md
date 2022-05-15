# Exercise

A legacy code base has been migrated into a new Spring Boot application.
It uses a SQL database, a REST design, and has a pre-packaged AngularJS frontend.

## Task

The application is going through its final code review, but serious doubts were
raised about the quality of the application. The reviewers recommend a full
rewrite of the backend application.

Your task is to identify and fix all flaws in the code. It should be ready to
go into production after this. You can ignore the frontend entirely, including
breaking its entire functionality fully. Because of this, recommendations also
include redesigning the REST endpoints and having properly defined behaviour.
This will require rewriting the application fully.

Testing should be done with both unit tests, integration tests, and you testing
the application in practice by running requests against it. Use any of `curl`,
[Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/), or
whichever tool you prefer.

Zip and upload your solution to the folder you downloaded it from on Google
Drive. Alternatively, e-mail it back, and we'll take care of the rest. This is
only how we do exercises; real-life work would not include uploading code to
Drive or e-mailing it around.

## Scope

Focus only on the backend. You may still update the frontend if you would like,
but it will not be evaluated.

If you did not get to finish rewriting the application in time, please make a
file with tasks you did not get done. This should improve general improvements
about the service. Hand it in alongside your submission. Let this to-do list be
as full of information as you can.

### Business scope

In reality, we will have certain scopes provided to us by the business side, or
by other teams. This coding exercise doesn't actually have any kind of scope,
but let this serve as a scope for your reimplementation, nonetheless.

> We need you to provide us with an API for registering in and managing
> tournaments and participants ("players" from hereon out).
>
> A tournament is some kind of event or challenge going on which has an ID,
> a name, and some kind of reward prize. All prizes will be in EUR to begin
> with, but we may want to expand to other markets with other currencies later
> on.
>
> A player will have an ID and a name. They may register to tournaments; store
> this however you want, so long as we can get all players in a tournament
> somehow.

While we often don't have real deadlines in practice, you may be given one for
the sake of this exercise. See the top part of the `Scope` section for more
info on how to handle unfinished tasks.

## Allowed dependencies

You are allowed to use any dependencies you would like, so long as they are not
malicious. Feel free to add or remove them as you please, including annotation
processors and nullness/contract annotation libraries. We want you to show how
you would ideally write an application with your current knowledge.

Our only ask is that you keep the language as Java. Kotlin, Scala, and any
other JVM-based programming languages are not in scope for this exercise.
We encourage you to use Java 11 or newer; Java 11 is the current default.

## Help

You're always welcome to use online material to get help. Make sure you
evaluate good sources like you would on the job, and give proper attribution.
If you can't find what you need online, please do reach out to us.

## Running the application

If you use Linux or macOS:

  - Open a terminal.
  - Run `./mvnw spring-boot:run`.
    * If this fails, ensure it is actually executable and retry: `chmod +x ./mvnw`.
	* If it still fails for reasons you cannot figure out, please contact us.
  - Head to [http://localhost:8080](http://localhost:8080/) to see the frontend.

If you use Windows:

  - Open a Windows Terminal, Command Shell, or PowerShell.
  - Run `.\mvnw.cmd spring-boot:run`.
	* If it fails for reasons you cannot figure out, please contact us.
  - Head to [http://localhost:8080](http://localhost:8080/) to see the frontend.

NOTE: You will need Java 11 (or newer) set up and in use for this to work.

-- TODO:

PRIO: DATABAS - GÖR SÅ DU KAN DELETA
1. Sätt upp API (POSTMAN) för Exercise. X
2. Fixa controller till Exercise. X
3. lägg till saveModel/partialUpdateModel (put/patch) i alla controllers - https://www.baeldung.com/http-put-patch-difference-spring
4. create service-classes for models. Current held in controllers "business logic".
5. Bygg på mina scenarion
6. namnge scenariona korrekt
7. Skapa en README.md - Ge POSTMAN-instruktioner, scopet, reflektioner, instruktioner till att köra programmet, m.m.  
8. Testa API med postman - Run PAF. X
9. Lägg till ResponseEntity<Model"> - se länk i 2. (LOW)
10. kolla om id kan tas in som en Long från pathVariable - se länk i 2. X
11. kolla skillnaden i databasen då du använder cascade.merge vs cascade.all
12. attending players låter fel. X
13. gå igenom kommentarer i alla filer
14. skapa unit/integrations-tester

Affärslogik service klassen: 

1. hämta ut spelare som tillhör en viss tunering samt presentera i postman. - Se bort kommenterad kod i CommandRunner. 
2. Ta bort spelare som tillhör en viss turnering X - görs i modellens @DeleteMapping
3. kasta errors som t.ex deletion failed
4. current state för att visa vilka matcher som pågår både turneringarna samt deltagarna.

JUST NU: Databas relations krasch shits


Reflektion: 
- Berätta om hur du valde att ta dig runt cascade med att deleta ur tabellerna direkt (fulhack)