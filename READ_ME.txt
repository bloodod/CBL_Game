Chimpanzee Remembers
27/10/2024

Authors:
Danilo Stessen (1708325)
Ariq Rhamizah (1667319)

Tutorial: 
The user will see numbers on the screen for 3 seconds. After 3 seconds the numbers disappear. 
The user must then click the numbers in order from low to high. If the user correctly clicks the numbers in the correct order
the user goes to the next round where an extra number is added. This continues until the user gets the order wrong or
clicks the wrong position. There is also a powerup that is a gamble and either shows
the position of the numbers or it ends the game with a 1/4 chance of showing the numbers.

The implemented learning goals were:
1) Version control (git):
https://journals.plos.org/ploscompbiol/article/file?id=10.1371/journal.pcbi.1004668&type=printable
Version control was implemented by using GitHub. In GitHub you create a repository where files can be shared.
People can work on these files and then 'commit' their files to the repository. They then 'push' the commit to the origin remote.
The other person can then 'fetch' the origin. The changes that were made are now adopted and thus easily shared. In GitHub you can 
easily see the changes that you made and also the history of all the changes that were made. The repository had to be cloned first 
from the online website before it could be used in the GitHub app. GitHub was used continuously throughout the project.

https://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=6188603
This article talks about how Git works on an introductory level. Git manages revisions instead of versions. Locally, a complete
version of your repository is stored. You could work and commit individual changes without requiring an internet connection.
Then it can be pushed when you are online again. Due to the local staging it is possible to edit, reorder, and squash together the 
past commits (also known as rebase).

2) User experience (UX):
https://pressbooks.pub/lidtfoundations/chapter/user-experience-design/
We mostly implemented functional prototyping as mentioned in this article. 
We made the prototype of the game and then got other people to play and give feedback.
The feedback was then implemented and then the people got to play the game again and gave new feedback.
This strategy (the successive approximation model) was adopted from Figure 2 in this article.
It should be noted that this article is more about user experience in general and not necessarily user 
experience in games.

https://link.springer.com/article/10.1007/s10639-018-9730-6
This article mentioned that the aesthetics could enhance cognitive and behavioral effects.
These skills can transfer to real life. Furthermore, it is mentioned that well-designed games implement a
method of scaffolding for gradually familiarizing the players. This is done in our game by increasing the 
amount of numbers that you have to remember. Having goals in the game could significantly influence player 
engagement. Moreover, auditory elements have been added to create a more immersive experience for the player.
There are feedback mechanisms in the game by changing the color of the button that is clicked to give the 
player a sense of satisfaction if it was correct or a sense of sadness if it was wrong.


We got feedback from a few people who volunteered to play our game, while it was still on development. 
Most of them found the game mechanics really easy to understand play the game itself, 
however, most had complaints or criticism regarding our User Interface. 
At the start, we did not have a lot of the features that are now implemented. For example, the sound button was 
added recently due to complaints of song being too distracting. It was set at the maximum volume and the user
had no option to change the volume within the game. This goes to show that we are implementing the 
user's needs in the form of a volume slider. Next, the end page, that also shows your score and 
the highest score was added. We added the color green for every tile that the player clicks correctly and
red for when the player clicks incorrectly. We chose the color green as it symbolizes success and red for 
an error or warning - in this case, the player loses the game. Stressful music in the form of Moonlight 
Sonata, the Third Movement, which is a very fast paced composition by Ludwig van Beethoven was used to 
intensify the player's feelings when playing this game. Unfortunately, long-term testing has not been done
to check if the players remained engaged without frustration or boredom. 



