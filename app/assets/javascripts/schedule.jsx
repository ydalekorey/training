(function (React, ReactDOM, $, moment) {
    var Schedule = React.createClass({
        componentDidMount: function () {

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
                events: '/appointments',
                eventDataTransform: function (appointment) {
                    var event =  {
                        title: "title",
                        start: appointment.startTime,
                        end: moment(appointment.startTime).add(moment.duration(appointment.duration)).format()
                    };
                    return event
                },
                allDaySlot: false,
                editable: true,
            });
        },
        shouldComponentUpdate: function () {
            return false;
        },
        render: function () {
            return (
                <div className="row">

                    <div className="col-md-3">
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
    ReactDOM.render(<Schedule />, document.getElementById('react-content'));
}(React, ReactDOM, $, moment));
