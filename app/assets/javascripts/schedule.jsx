(function (React, ReactDOM, $) {
    var Schedule = React.createClass({
        componentDidMount: function () {

            /* initialize the external events
             -----------------------------------------------------------------*/
            function ini_events(ele) {
                ele.each(function () {

                    // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                    // it doesn't need to have a start or end
                    var eventObject = {
                        title: $.trim($(this).text()) // use the element's text as the event title
                    };

                    // store the Event Object in the DOM element so we can get to it later
                    $(this).data('eventObject', eventObject);

                    // make the event draggable using jQuery UI
                    $(this).draggable({
                        zIndex: 1070,
                        revert: true, // will cause the event to go back to its
                        revertDuration: 0  //  original position after the drag
                    });

                });
            }

            ini_events($('#external-events div.external-event'));

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
                events: '/appointments',
                eventDataTransform: function (appointment) {
                    return {
                        title: "title",
                        start: appointment.startTime
                    }
                },
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar !!!
                drop: function (date, allDay) { // this function is called when something is dropped

                    // retrieve the dropped element's stored Event Object
                    var originalEventObject = $(this).data('eventObject');

                    // we need to copy it, so that multiple events don't have a reference to the same object
                    var copiedEventObject = $.extend({}, originalEventObject);

                    // assign it the date that was reported
                    copiedEventObject.start = date;
                    copiedEventObject.allDay = allDay;
                    copiedEventObject.backgroundColor = $(this).css("background-color");
                    copiedEventObject.borderColor = $(this).css("border-color");

                    // render the event on the calendar
                    // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }

                }
            });

            /* ADDING EVENTS */
            var currColor = "#3c8dbc"; //Red by default
            //Color chooser button
            var colorChooser = $("#color-chooser-btn");
            $("#color-chooser > li > a").click(function (e) {
                e.preventDefault();
                //Save color
                currColor = $(this).css("color");
                //Add color effect to button
                $('#add-new-event').css({"background-color": currColor, "border-color": currColor});
            });
            $("#add-new-event").click(function (e) {
                e.preventDefault();
                //Get value and make sure it is not null
                var val = $("#new-event").val();
                if (val.length == 0) {
                    return;
                }

                //Create events
                var event = $("<div />");
                event.css({
                    "background-color": currColor,
                    "border-color": currColor,
                    "color": "#fff"
                }).addClass("external-event");
                event.html(val);
                $('#external-events').prepend(event);

                //Add draggable funtionality
                ini_events(event);

                //Remove event from text input
                $("#new-event").val("");
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
                                        <li><a className="text-light-blue" href="#"><i className="fa fa-square"/></a>
                                        </li>
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
                                    <input id="new-event" type="text" className="form-control"
                                           placeholder="Event Title"/>

                                    <div className="input-group-btn">
                                        <button id="add-new-event" type="button" className="btn btn-primary btn-flat">
                                            Add
                                        </button>
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
    ReactDOM.render(<Schedule />, document.getElementById('react-content'));
}(React, ReactDOM, $));
