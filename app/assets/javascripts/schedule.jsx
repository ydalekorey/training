(function (React, ReactDOM, $) {
    var Schedule = React.createClass({
        componentDidMount: function () {
            var date = new Date();
            var d = date.getDate(),
                m = date.getMonth(),
                y = date.getFullYear();
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                buttonText: {
                    today: 'today',
                    month: 'month',
                    week: 'week',
                    day: 'day'
                },
                //Random default events
                events: [
                    {
                        title: 'All Day Event',
                        start: new Date(y, m, 1),
                        backgroundColor: "#f56954", //red
                        borderColor: "#f56954" //red
                    },
                    {
                        title: 'Long Event',
                        start: new Date(y, m, d - 5),
                        end: new Date(y, m, d - 2),
                        backgroundColor: "#f39c12", //yellow
                        borderColor: "#f39c12" //yellow
                    },
                    {
                        title: 'Meeting',
                        start: new Date(y, m, d, 10, 30),
                        allDay: false,
                        backgroundColor: "#0073b7", //Blue
                        borderColor: "#0073b7" //Blue
                    },
                    {
                        title: 'Lunch',
                        start: new Date(y, m, d, 12, 0),
                        end: new Date(y, m, d, 14, 0),
                        allDay: false,
                        backgroundColor: "#00c0ef", //Info (aqua)
                        borderColor: "#00c0ef" //Info (aqua)
                    },
                    {
                        title: 'Birthday Party',
                        start: new Date(y, m, d + 1, 19, 0),
                        end: new Date(y, m, d + 1, 22, 30),
                        allDay: false,
                        backgroundColor: "#00a65a", //Success (green)
                        borderColor: "#00a65a" //Success (green)
                    },
                    {
                        title: 'Click for Google',
                        start: new Date(y, m, 28),
                        end: new Date(y, m, 29),
                        url: 'http://google.com/',
                        backgroundColor: "#3c8dbc", //Primary (light-blue)
                        borderColor: "#3c8dbc" //Primary (light-blue)
                    }
                ],
                editable: true
            });
        },
        shouldComponentUpdate: function () {
            return false;
        },
        render: function () {
            return (
                <div className="row">

                    <div className="col-md-3">
                        <div className="box box-solid">
                            <div className="box-header with-border">
                                <h4 className="box-title">Draggable Events</h4>
                            </div>
                            <div className="box-body">
                                {/*the events*/}
                                <div id="external-events">
                                    <div className="external-event bg-green">Lunch</div>
                                    <div className="external-event bg-yellow">Go home</div>
                                    <div className="external-event bg-aqua">Do homework</div>
                                    <div className="external-event bg-light-blue">Work on UI design</div>
                                    <div className="external-event bg-red">Sleep tight</div>
                                    <div className="checkbox">
                                        <label htmlFor="drop-remove">
                                            <input type="checkbox" id="drop-remove"/>
                                                remove after drop
                                        </label>
                                    </div>
                                </div>
                            </div>
                            {/*/.box-body*/}
                        </div>
                        {/*/. box*/}
                        <div className="box box-solid">
                            <div className="box-header with-border">
                                <h3 className="box-title">Create Event</h3>
                            </div>
                            <div className="box-body">
                                <div className="btn-group" style={{width: '100%', marginBottom: '10px'}}>
                                    <ul className="fc-color-picker" id="color-chooser">
                                        <li><a className="text-aqua" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-blue" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-light-blue" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-teal" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-yellow" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-orange" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-green" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-lime" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-red" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-purple" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-fuchsia" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-muted" href="#"><i className="fa fa-square"/></a></li>
                                        <li><a className="text-navy" href="#"><i className="fa fa-square"/></a></li>
                                    </ul>
                                </div>
                                {/*/btn-group*/}
                                <div className="input-group">
                                    <input id="new-event" type="text" className="form-control" placeholder="Event Title"/>

                                        <div className="input-group-btn">
                                            <button id="add-new-event" type="button" className="btn btn-primary btn-flat">Add</button>
                                        </div>
                                    {/*/btn-group*/}
                                </div>
                                {/*/input-group*/}
                            </div>
                        </div>
                    </div>
                    
                    {/*/.col*/}
                    <div className="col-md-9">
                        <div className="box box-primary">
                            <div className="box-body no-padding">
                                {/*THE CALENDAR*/}
                                <div ref="calendar" id="calendar"></div>
                            </div>
                            {/*/.box-body*/}
                        </div>
                        {/*/. box*/}
                    </div>
                    {/*/.col*/}
                </div>
            );
        }
    });
    ReactDOM.render(
        <Schedule />,
        document.getElementById('react-content')
    );
}(React, ReactDOM, $));
