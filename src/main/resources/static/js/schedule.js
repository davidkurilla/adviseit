window.onload = async function (){
    const button = document.querySelector("button");
    button.onclick = addPreference;


}

async function addPreference(event){
    event.preventDefault();
    const summerQuarter = document.querySelector("#summerQuarter").value === "on";
    const newPreference = {
        coursesPerQuarter: document.querySelector("#classesPerQuarter").value,
        summerQuarter: summerQuarter,
        startingSeason: document.querySelector("#startingQuarter").value
    }

    //send preference data
    let url = "http://localhost:8080/api/v1/preferences/post"
    let config = {
        method: "post",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(newPreference)
    }
    let response = await fetch(url, config);
   // console.log(response);

    //build schedule
    url = "http://localhost:8080/api/v1/schedules/post/0"
    config = {
        method: "post",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(newPreference)
    }
    response = await fetch(url, config);
    //console.log(response);

    //pull schedule
     url = "http://localhost:8080/api/v1/schedules/get/id/0"
     config = {
        method: 'get'
    }

    response = await fetch(url, config);
    const data = await response.json();
    showSchedule(data);

}



function showSchedule(schedule){
    for (let i = 0; i < schedule.length; i++) {

        console.log(schedule.getQuarterList().get(i).getCourseList()[i]);

    }
}