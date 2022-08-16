import emailjs from 'emailjs-com';

export default {

        sendStatusUpdateEmail (pothole) {
           return emailjs.send('status_update_service','status_update',
        {
          status: pothole.statuses[pothole.statuses.length - 1].name,
          road_name: pothole.roadName,
          neighborhood: pothole.neighborhood,
          city: pothole.city,
          state: pothole.state,
          user_email: "fixmyroad.app@gmail.com",
          }, 'bRb3nMl54vtbGGDIK')
          .then((result) => {
            console.log('SUCCESS!', result.text);
        }, (error) => {
            console.log('FAILED...', error.text);
        });
    
        }
    
}