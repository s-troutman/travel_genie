# Capstone Proposals

## Problem Statement
   Vacation is an exciting time for everyone, and everyone wants something different out of their vacation. Some people want to relax, while others want to be active. Some people have a smaller budget, while others, money is not an issue. Some people have little kids that need to be catered to, while others are looking for solitude or romance. Some people prefer the beach, while others seek the tranquility of the woods.
   
   We all look for something different in a vacation. It can be difficult to find a perfect place that meets all our needs. There are a lot of research involved and that can be time consuming. This time can be a burden on top of other planning involved into taking a vacation.
   So, for the most of us, the hardest part of taking any vacation is deciding where to go. As indecision grows, clock ticks, and stress level creeps up, choosing a destination can become our worst enemy.

## Technical Solution
### Scenario 1
   Audrey is planning a lastminute solo vacation in a few weeks. She has been very stressed from work and want to relax. She thinks she is not going to want to do much but maybe one day during the vacation she would like to do something active. She lives in a big city, so she really wants to go somewhere with beautiful nature. She uses the Travel Genie to input her preferences. Travel Genie gave her a list of destinations that have beautiful scenery, plenty relaxation amenities, but also a few more engaging activities. She saves a few of them on her Wish List so she can look more into them. 
### Scenario 2
   Josh is planning a big family vacation for his grandmother who wants to treat everyone to a fun time on her birthday next year. She is forking the bills and grandma is generous. She wants Josh to find a place where everyone will love. Josh creates an account with Travel Genie and send the information to everyone in the family. In a few weeks, the Wish List Josh created is filled with destinations. Josh looked through the list, and checked out all the entertainments everyone is interested in. He was finally able to single out one place he thinks will please them all.

## Glossary
   ### City
   A city that is a possible vacation destination.
   ### Country
   The country of a city.
   ### Scenery
   A main characteristic of a city based on its geo-location. It can be beach, mountain, dessert, metropolitan, or snow.
   ### Entertainment
   An activity that a city offers. One city can offer multiple entertainment. One entertainment can be offered by multiple cities.
   ### Active Level
   The amount of physical demands an entertainment can assert on a person. It can be low, medium, or high.
   ### Price Range
   The amount of money an entertainment can cost per person. It can be $, $$ or $$$. $ is under 10 dollars, $$ is 10-100 dollars, $$$ is more than 100 dollars.
   ### Kid Friendly
   A yes or no attribute of an entertainment that speak to whether said entertainment is good for non-teenager children. Dolphin watching is kid friendly while sky diving is not.
   ### Wish
   A combination of the city, the country and all the entertainments offered by the city that matches a user’s preference.
   ### Wish List
   A list of wishes saved by the user to be viewed later.
   ### Genie
   The algorithm that matches a user’s preference to a city.
   ### User
   A potential user of the application who is looking to find a vacation destination that matches their preferences.

## High Level Requirement
### User
   1.	Create a wish.
   2.	Edit a wish.
   3.	Delete a wish.
   4.	Add a wish to the wish list.
   5.	View the wish list.
   6.	Sign up for an account (authenticated).

### User Stories/Scenarios
#### Create a Wish
   Making a wish with preference matching.
   Suggested data:
   1.	Scenery. (User must choose one or more scenery they prefer)
   2.	Active Level. (User must choose one or more active level they prefer)
   3.	Price Range. (User must choose one of more price range they prefer)
   4.	Kid Friendly. (User must choose either yes or don’t care)
   - Precondition: User must be logged in.
   - Post-condition: A list of cities with matching preference will be provided to the user for them to choose. They can choose one and add it to their wish list or choose to cancel and start over.  
#### Edit a Wish
   Can only edit the list entertainments offered by the city of a wish.
   - Precondition: User must be logged in and have at least one wish on their list.
   - Post-condition: A list of entertainment available in the city of a wish will be provided to the user for them to choose. They can choose more than one and add them to their wish. The wish will be updated, and change reflected in the Wish List.
#### Delete a Wish
   Can only delete one wish at a time.
   - Precondition: User must be logged in and have at least one wish on their list. User must confirm the delete via a pop-up window prompt.
   - Post-condition: City and entertainment are not affected. Only the reference to the user that is logged in. User will be asked to confirm the deletion. Once deleted, the wish is permanently erased.
#### View Wishes
   User will have two ways to view the wishes they saved.
   1.	List-view: a simple list with the country, the city, and number of entertainments chosen.
   2.	Detail-view: a text-based detail view with the country, the city, scenery, a list of entertainments with active level, price range, and kid friendly that was chosen.
   - Precondition: User must be logged in and have at least one wish on their list.
   - Post-condition: None
#### Apply for An Account
   Anyone can apply for account and need to apply for an account to use the app.
   - Precondition: None
   - Post-condition: User will get access to the Genie and their wish list. 
