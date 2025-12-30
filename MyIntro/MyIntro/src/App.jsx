import myVideo from './assets/editedVideo.mp4'
import './App.css'

function App() {
  return (
    <div className="main-container">
      <div className="content-wrapper">
        {/* Split Content Area */}
        <div className="split-view">
          {/* Left Side: Blue Box */}
          <div className="left-half">
            <div className="blue-box">
              <h1>Edward Eo</h1>
              <p>info to come</p>
            </div>
          </div>

          {/* Right Side: Video */}
          <div className="right-half">
            <video
              autoPlay
              muted
              loop
              playsInline
              width="100%"
            >
              <source src={myVideo} type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          </div>
        </div>
        <div className="gradient-spacer"></div>
      </div>

      {/* 2. The Right-Side Navbar */}
      <nav className="side-nav">
        <ul>
          <li>Home</li>
          <li>About</li>
          <li>Projects</li>
          <li>Contact</li>
        </ul>
      </nav>
    </div>
  )
}

export default App