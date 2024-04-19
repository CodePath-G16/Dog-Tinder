# **DOG TINDER**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)
   
## App Overview

### Description 

**An application that matches dog to potential owners. Just like the tinder app, it will display photo and the user will have the option to like or dislike. Users can also view dog info by using long click and view only liked dogs by clicking a button.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:**
    - Animal Adoption
- **Mobile:**
    - An android application, allowing for a quick and convenient access to view available dogs.
- **Story:**
    - The app's goal is to connect dogs in needs of homes with loving owners and responsible owners. By providing a user-friendly platform with dog profiles, it simplifies the adoption process for both potential owners and shelters.
- **Market:**
    - Any individual or family that are looking to adopt a dog. It could also extended to shelters that are looking to match dog with potential owners.
- **Habit:**
    - Users (want-to-be pet owners) use the app as a reliable source to search for available dogs.
- **Scope:**
    - It will display a list of available dogs photos for user to view
    - Have the ability to view more information such as name, age, breed, location, and description
    - It will include functionality to "like/favorite" that dog
       - can display a list favorites
       - include buttons to filter breeds

## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- **Like/Dislike Dogs - Implement a buton mechanism for users to like or dislike dogs**
- **Favorites List - Allow users to view a list of dogs that they have liked**
- **Detailed Dog View - Enable users to view more details about the dog by long clicking or long pressing the dog's photo**
- **Dog Profile - display photo and their detailed information - name, breed, age, gender, location**
- **Search/Filter - a search/filter feature to allow users to find dogs based on specific criteria such as breed and age**

Stretch Features:

- **Chat - implement a chat feature that allows users to communicate with the shelter or dog foster family to ask questions or arrange a meeting**
- **Map integration - show the location off each dog on a map to help users find dogs that are geographically close to them**
- **Compatibility test - a fun quiz or test that helps users determine their compatibity with a specific dog based on their lifestyle.**


### 2. Chosen API(s)

- **AdoptAPet API - https://documenter.getpostman.com/view/17710041/UUy38kte**
    - **Get Dog:** Retrieve random dog for user to view
       - => Display dog photo
    - **Get Dog:** When user long clicks a photo
       - => Display name, gender, location
    - **Get Dog:** User can view dog profile
       - => Display name, age, gender, location, contact info if available
    - **Get Dog-Breed:** User can search/filter for dogs from a spefic breed
       - => Display dogs that are a specific breed that the user searches for
    - **Get Dog-Age:** User can search/filter for dogs based on age
       - => Display dogs that are a specific age

### 3. User Interaction

Required Feature

- **Like**
  - => **Action: When the user likes a dog, the dog's profile will be added to the only liked dog list**
  - => **Outcome: User cna easily access and review the profiles of dogs they have liked**
  - => **Aditional Action: Option to connect with the shelter to move forward with adoption process or get more information such as it's location and shelter contact information**
    
- **Dislike**
  - => **Action: When a user dislikes a dog, the dog's profile will be added to disliked list**
  - => **Outcome: Users can keep track of disliked dogs and avoid seeing them in future recommendations**
    
- **Long click on the dog photo**
    - => **Action: When a user long clicks a dog's photo, it will display more information about the dog**
    - => **Outcome: Displays more information such as age, gender, and location**
 
Stretch Feature

- **Chat**
    - => **Action: chat feature that will allow users to communicate with shelter staff or foster dog family**
    - => **Outcome: User can ask questions, get more information, or arrange a meeting**
 
- **Map Integration**
    - => **Action: Show the location of each dog on map to help users find dogs that are close to them**
    - => **Outcome: User can easily locate local dogs that are available for adoption**

- **Compatibility Test**
    - => **Action: Take a quick quick test to test the compatibility with a specific dog**
    - => **Outcome: Able to determine is the dog is a good fit for the user's lifestyle such as if the dog is good with kids or other pets**

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
<img src="https://i.imgur.com/A3Y5AgE.png" width=600>
<img src="https://i.imgur.com/qT9bv6Z.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

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
