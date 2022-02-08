
import pygame
import random

pygame.init()

screen_w = 1050
screen_h = 690

##set uo the screen and load the prize foto to the award variable  
screen = pygame.display.set_mode((screen_w,screen_h))

player = pygame.image.load("image.png")
enemy = pygame.image.load("enemy.png")
award = pygame.image.load("prize.jpg")

###Get the width and height for all the images

image_height = player.get_height()
image_width = player.get_width()
enemy_height = enemy.get_height()
enemy_width = enemy.get_width()
award_width = award.get_width()
award_height =  award.get_height()


##Set the X and Y pos of all the images and the enemies 
playerXPosition = 150
playerYPosition = 40

enemyXPosition =  screen_w
enemyYPosition =  random.randint(0, screen_h - enemy_height)

enemy2X = screen_w
enemy2Y = random.randint(0, screen_h - enemy_height)

enemy3X = screen_w
enemy3Y = random.randint(0, screen_h - enemy_height)


awardXPos = screen_w
awardYpos = random.randint(0,screen_h - award_height)


keyUp= False
keyDown = False
keyRight = False
keyLeft = False
while 1: # This is a looping structure that will loop the indented code until you tell it to stop(in the event where you exit the program by quitting). In Python the int 1 has the boolean value of 'true'. In fact numbers greater than 0 also do. 0 on the other hand has a boolean value of false. You can test this out with the bool(...) function to see what boolean value types have. You will learn more about while loop structers later. 
    
    #Make all the enemies and reward images visible 

    screen.fill(0) # Clears the screen.
    screen.blit(player, (playerXPosition, playerYPosition))# This draws the player image to the screen at the postion specfied. I.e. (100, 50).
    screen.blit(enemy, (enemyXPosition, enemyYPosition))
    screen.blit(enemy,(enemy2X,enemy2Y))
    screen.blit(award, (awardXPos,awardYpos))
    screen.blit(enemy,(enemy3X,enemy3Y))
    
    
    
    pygame.display.flip()# This updates the screen. 
    
    # This loops through events in the game.
    
    for event in pygame.event.get():
    
        # This event checks if the user quits the program, then if so it exits the program. 
        
        if event.type == pygame.QUIT:
            pygame.quit()
            exit(0)
        # This event checks if the user press a key down.
        
        if event.type == pygame.KEYDOWN:
        
            # Test if the key pressed is the one we want.
            ##Test if the Left or right keys are pressed
            if event.key == pygame.K_UP: # pygame.K_UP represents a keyboard key constant. 
                keyUp = True
            if event.key == pygame.K_DOWN:
                keyDown = True
            elif event.key == pygame.K_LEFT:
                keyLeft = True
            elif event.key == pygame.K_RIGHT:
                keyRight = True
        
        # This event checks if the key is up(i.e. not pressed by the user).
        
        if event.type == pygame.KEYUP:
        
            ##Test which button is released and change to false

            if event.key == pygame.K_UP:
                keyUp = False
            if event.key == pygame.K_DOWN:
                keyDown = False
            elif event.key == pygame.K_LEFT:
                keyLeft = False
            elif event.key == pygame.K_RIGHT:
                keyRight = False
            
    # After events are checked for in the for loop above and values are set,
    # check key pressed values and move player accordingly.
    
    # The coordinate system of the game window(screen) is that the top left corner is (0, 0).
    # This means that if you want the player to move down you will have to increase the y position. 
    
    if keyUp == True:
        if playerYPosition > 0 : # This makes sure that the user does not move the player above the window.
            playerYPosition -= 1
    if keyDown == True:
        if playerYPosition < screen_h - image_height:# This makes sure that the user does not move the player below the window.
            playerYPosition += 1
    
    ##IF the keyRight is pressed then move the player position one to the right only of it wont go over the screem
    ##IF Keyleft is pressed move the player one to the left only if it wont go over the screen
    elif keyRight ==True:
        if playerXPosition <screen_w - image_width :
            playerXPosition+=1 
    elif keyLeft ==True:
        if playerXPosition> 0 :
            playerXPosition-=1
        
    
    # Check for collision of the enemy with the player.
    # To do this we need bounding boxes around the images of the player and enemy.
    # We the need to test if these boxes intersect. If they do then there is a collision.
    
    # Bounding box for the player:
    
    playerBox = pygame.Rect(player.get_rect())
    
    # The following updates the playerBox position to the player's position,
    # in effect making the box stay around the player image. 
    
    playerBox.top = playerYPosition
    playerBox.left = playerXPosition
    
    # Bounding box for the enemy:
    # Bound the box for all players and the award
    enemyBox = pygame.Rect(enemy.get_rect())
    enemyBox.top = enemyYPosition
    enemyBox.left = enemyXPosition

    enemy2Box = pygame.Rect(enemy.get_rect())
    enemy2Box.top = enemy2Y
    enemy2Box.left = enemy2X

    enemy3Box = pygame.Rect(enemy.get_rect())
    enemy3Box.top = enemy3Y
    enemy3Box.left = enemy3X

    awardBox = pygame.Rect(award.get_rect())
    awardBox.top = awardYpos 
    awardBox.left = awardXPos 
    

    # Test collision of the boxes:
    ## If the player collides with any of the enemies then the game is over and the player loses
    
    if (playerBox.colliderect(enemyBox)) or (playerBox.colliderect(enemy2Box)) or (playerBox.colliderect(enemy3Box)) :
    
        # Display losing status to the user: 
        
        print("You lose!")
       
        # Quite game and exit window: 
        
        pygame.quit()
        exit(0)
    
   
    # If  all the enemies are off the screen the user wins the game , or of the player box collides with the awardBox
    
    if (enemyXPosition < 0 - enemy_width) and (enemy3X < 0 - enemy_width) and (enemy2X < 0 - enemy_width) or playerBox.colliderect(awardBox):
    
        # Display wining status to the user: 
        
        print("You win!")
        
        # Quite game and exit window: 
        pygame.quit()
        
        exit(0)
    
    


    
    
   

 
    
    # Make enemies  approach the player and the award come last.
    
    enemyXPosition -= 0.35
    awardXPos -= 0.10
    enemy2X -= 0.22
    enemy3X -=0.11
    # ================The game loop logic ends here. =============
  
