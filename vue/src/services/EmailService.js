import emailjs from 'emailjs-com';

export default {

    getRecipients(pothole) {
        let recipients = "";
        pothole.statuses.forEach(status => {
            if (status.name == "Subscribed") {
                recipients = recipients + (status.email + ';');
            }
        });
        return recipients;
    },

    sendStatusUpdateEmail(pothole) {
        return emailjs.send('status_update_service', 'status_update',
            {
                status: this.lastPublicStatus(pothole),
                road_name: pothole.roadName,
                neighborhood: pothole.neighborhood,
                city: pothole.city,
                state: pothole.state,
                user_email: this.getRecipients(pothole),
            },
            'bRb3nMl54vtbGGDIK')
            .then((result) => {
                console.log('SUCCESS!', result.text);
            }, (error) => {
                console.log('FAILED...', error.text);
            });

    },

    lastPublicStatus(pothole){
        let filteredStatuses = [];
        pothole.statuses.forEach((status) => {
          if (status.public == true){
            filteredStatuses.push(status);
          }
        });
        const lastPublicStatus = filteredStatuses[filteredStatuses.length -1].name;
        return lastPublicStatus;
      }
    

}