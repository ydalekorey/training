(function (React, ReactDOM) {
    var CommentBox = React.createClass({
        render: function () {
            return (
                <div className="commentBox">
                    Hello, world! I am a CommentBox in immediate function and Play Application.
                </div>
            );
        }
    });
    ReactDOM.render(
        <CommentBox />,
        document.getElementById('react-content')
    );
}(React, ReactDOM));
