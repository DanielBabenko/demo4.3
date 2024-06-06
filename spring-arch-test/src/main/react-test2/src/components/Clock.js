import React, { Component } from 'react';
class Clock extends Component {
    UPDATE_INTERVAL_IN_MS = 6000;

    months = ['января','февраля','марта','апреля','мая','июня','июля','августа','сентября','октября','ноября','декабря'];
    weekdays = ['Воскресенье','Понедельник','Вторник','Среда','Четверг','Пятница','Суббота'];
    constructor(props){
        super(props);
//This declared the state of time at the very beginning
        this.state ={
            time: new Date().toLocaleTimeString(),
            weekday: this.weekdays[new Date().getDay()],
            day: new Date().getDate(),
            month: this.months[new Date().getMonth()],
            year: new Date().getFullYear()
        }
    }

//This happens when the component mount and the setInterval function get called with a call back function updateClock()
    componentDidMount() {
        this.intervalID = setInterval(() =>
                this.updateClock(),
            this.UPDATE_INTERVAL_IN_MS
        );}

//This section clears setInterval by calling intervalID so as to optimise memory
    componentWillUnmount(){
        clearInterval(this.intervalID)
    }

//This function set the state of the time to a new time
    updateClock(){
        this.setState({
            time: new Date().toLocaleTimeString(),
            weekday: this.weekdays[new Date().getDay()],
            day: new Date().getDate(),
            month: this.months[new Date().getMonth()],
            year: new Date().getFullYear()
        });
    }
    render() {
        return (
            <div className="Time">
                <span> {this.state.weekday}</span>,
                <span> {this.state.day}</span>
                <span> {this.state.month}</span>,
                <span> {this.state.year}</span> гг.
                <p id="time"> {this.state.time}</p>
            </div>
        );
    }
}

export default Clock;