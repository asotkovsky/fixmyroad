# FixMyRoad 

FixMyRoad is an application that citizens can use to report potholes to their cities for repair. As an admin or an employee of the city, you can review reports, assign employees to a pothole, update status, and schedule potholes for inspection/repair among other features. Citizens can subscribe to email updates on a specific pothole and follow the status from it's initial report to eventual repair. 

<h3>Some context:</h3>
I worked on this project as 1 on a team of 4 developers for our final capstone project at Tech Elevator (an intensive 14-week coding bootcamp). What really excited me about this project was the user experience first approach that we took with our design. We spent a lot of time putting ourselves in the user's shoes and considering features or design choices that may make the site less intuitive. We built in error handling to keep the user on track with their task. I think that consideration is evidenced in the usability and clean styling of our end product. 

What's next?

- I plan on deploying the app to Heroku soon so anyone interested can interact with FixMyRoad directly! 
- Developing API documentation

## Logging In

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/login.png" width="500">

- Users can login or register a new account from the login page
- Unathenticated users can navigate to the reported potholes page to review existing potholes

## Home Page

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/home-page.png" width="500">

The site greets the logged in user where they can chose to navigate to:

    - Report a new pothole
    - Review reported potholes
    
## Report Pothole Form

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/report-form.png" width="500">

Users must drop a pin on a map that is centered on their current location and select a severity on a scale of 1 - 5

    - Metadata about the pin location is displayed including:
        - Road Name
        - Neighborhood (if available)
        - City Name and State
        
Other optional fields include:

        - Location on Roadway (Road or Shoulder)
        - Description
        - Image upload (preview displays once uploaded)
        - Subscription to status update emails
        
Upon submission user is directed to a list of reported potholes
        
## Reported Potholes

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/user-pothole-list.png" width="500"><img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/employee-pothole-list.png" width="500">

The view of this page varies depending on if you are an authenticated user/citizen or admin. Both versions feature a map that syncs with the filters that have been applied by the user.

Employees/admins can drag and drop unassgined potholes to the employees on the bottom of the page. Clicking on an employee filters the list and the map down to only potholes that are assigned to that particular employee.

## Pothole Detail View

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/user-detail-view.png" width="500"><img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/employee-details-view.png" width="500">

Clicking the blue plus icon on a pothole from the list view takes you to a modal with more detailed information about the pothole.
Again the details view depends on what type of user is viewing the page. 

Both users can see:

  - Details of pothole
  - Photo (if available)
  - Timeline of pothole statuses and scheduled inspection/repairs
  - Option to subscribe to email updates
  
Citizens can:

    - "Notice" a pothole which emphasizes a pothole that someone else has reported and can help employees prioritize

Admins can:

    - Update status of pothole
    - Schedule inspection or repair
    - Delete a pothole
    
<h3>And just for fun ... our 404 page:</h3>

<img src="https://github.com/asotkovsky/fixmyroad/blob/main/fixmyroad-screenshots/404-page.png" width="500">

