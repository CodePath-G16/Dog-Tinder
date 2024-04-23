# **DOG TINDER**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)
   
## App Overview

### Description 

**An application that matches dog to potential owners. Just like the tinder app, it will display photo and the user will have the option swipe to see more dogs. To view more specific dog information, the user can long click to a profile about the dog and return to viewing more dogs.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:**
    - Animal Adoption
- **Mobile:**
    - An android application, allowing for a quick and convenient access to view available dogs.
- **Story:**
    - The app's goal is to connect dogs in needs of homes with loving owners and responsible owners. By providing a user-friendly platform with dog profiles, it simplifies the search process for potential owners.
- **Market:**
    - Any individual or family that are looking to adopt a dog. It could also extended to shelters that are looking to match dog with potential owners.
- **Habit:**
    - Users (want-to-be pet owners) use the app as a reliable source to search for available dogs.
- **Scope:**
    - It will display a list of available dogs photos for user to view
    - Have the ability to view more information such as breed name, weight, height, description, etc.
    - It will include functionality to notify the user "like/favorite" or "dislike" that dog
      

## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- **Detailed Dog View / Dog Profile SPECIFIC INFO - Enable users to view more details (breed name, weight, height, bred for, life-span, etc.) about the dog by long clicking or long pressing the dog's photo**
- **Dog Recycler Home View - display photo and their general information - breed name, weight, height**
- **Welcome/Login Screen - input for username and password, leads to home page**
- **Notify Like/Dislike Dogs - Implement a button mechanism to notify (display a Toast) users they liked/disliked the dog**


Stretch Features:
- **Favorites List - Allow users to view a list of dogs that they have liked**
- **Search/Filter - a search/filter feature to allow users to find dogs based on specific criteria such as breed and age**
- **Chat - implement a chat feature that allows users to communicate with the shelter or dog foster family to ask questions or arrange a meeting**


### 2. Chosen API(s)

- **The Dog API - https://documenter.getpostman.com/view/5578104/2s935hRnak#587d112a-cfbb-4817-9a84-98a606113973**
    - **Get Dog:** Retrieve random dog for user to view
       - => Display dog photo
    - **Get Dog-Breed:** When user long clicks a photo, get that specific breed's info
       - => Display breed name, weight, height, characteristic, etc.
    - **Get Dog:** User can view dog profile
       - => Display weight, height, breed name, temperment, bred for, life span

### 3. User Interaction

Required Feature

- **Long click on the dog photo**
	- => **Action: When a user long clicks a dog's photo, it will display more information about the dog**
	- => **Outcome: User learns more information about the dog**
- **Scroll**
	- => **Action:: Scroll through recycle view of available dogs**
	- => **Outcome: Displays more available dogs**
- **Click on Dislike/Like button will display Dislike/Like Toasts**
	- => **Action: When the user likes/dislikes a dog, notify user**
	- => **Outcome: User is notified if they will have that dog in their likes or dislikes (no longer displayed)**
    

Stretch Feature

- **Favorites List - Allow users to view a list of dogs that they have liked**
	- => **Action: When user clicks the like button, that dog is added to favorites list**
	- => **Outcome: User can more easily refer to dogs they are interested in**
- **Search/Filter - A search/filter feature to allow users to find dogs based on specific criteria such as breed and age**
	- => **Action: Provide text bar for user to type in the criteria**
	- => **Outcome: User can view more specific dogs that fit their interests**
- **Chat - implement a chat feature that allows users to communicate with the shelter or dog foster family to ask questions or arrange a meeting**
	- => **Action: Provide a page the connects the user to the approriate shelter for further information about the dog**
	- => **Outcome: User has easier access to learning more about the dog**

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
<img src="https://i.imgur.com/FyXcVcG.png" width=800>


### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  
 - Understand navigation best practices
 - Underestand how to pass information to another page
 - Understand endpoints and which parameters to pass into the query string
 - How to debug and test functionality utilizing log cat, toasts, etc.

For Milestone 2, include **2+ Videos/GIFs** of the build process here!
Video 1: https://i.imgur.com/hoVi8yP.mp4
VIdeo 2: https://i.imgur.com/QKYT1EJ.mp4



## License

Copyright **2024** **Annamarie Cortes, Reneca Capuno, Deki Pelshog, Ekaterina Krasnoperova**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
