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

    sendStatusUpdateEmail(pothole, status) {
        return emailjs.send('status_update_service', 'status_update',
            {
                status: status.name,
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


}