import myVideo from './assets/editedVideo.mp4'
import uniFlag from './assets/uottawa-logo.png'
import uniCampus from './assets/uottawa-campus.jpg'
import uniStem from './assets/uottawa-STEM.jpg'
import './App.css'
import { useState, useEffect } from 'react';
import SkillsCarousel from './SkillsCarousel'

const images = [
  uniCampus, uniFlag, uniStem
];

const age = new Date().getFullYear() - 2005;

const projects = [
  {id: 1, title: "Messaging Program", desc: "Description 1", address: "https://github.com/Edward46282/side-project/tree/main/In%20third%20year%20(network)"},
   {id: 2, title: "Todo Android app", desc: "Description 2", address: "https://github.com/Edward46282/side-project/tree/main/After%20finishing%202nd%20year%20(todo%20list)/ToDo%20app"}]

  function App() {
  return (
    <div className="main-container">
      <div className="content-wrapper">
        {/* Split Content Area */}
        <div id="mainScreen" className="split-view">
          {/* Left Side: Blue Box */}
          <div className="left-half">
            <div className="black-box">
              <h1>Edward Eo</h1>
              <div className="lineTag"></div>
              <p><span className="highlight-bold">Born:</span> 2005
                <br /><span className="highlight-bold">Age:</span> {age} years old 
                <br /><span className="highlight-bold">Things I like to do:</span> watch baseball, play video games, watch movies
              </p>
              <div className="social-links">
                <a href="https://www.instagram.com/edward99929/" target="_blank" rel="noopener noreferrer">
                  <i class="fa-brands fa-instagram" aria-label="Instagram logo"></i>
                </a>
                <a href="https://github.com/Edward46282" target="_blank" rel="noopener noreferrer">
                  <i class="fa-brands fa-github" aria-label="Github logo"></i>
                </a>
                <a href="https://www.linkedin.com/in/edward-eo-74068b2b5/" target="_blank" rel="noopener noreferrer">
                  <i class="fa-brands fa-linkedin" aria-label="Linkedin logo"></i>
                </a>
              </div>
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
        <div id="education" className="education-container">
          <div className="section-title">
            <h1 style={{ fontStyle: 'italic' }}>Education</h1>
          </div>
          <div className= "education-content">
            <div className = "uniPhoto">
              <ImageCard />
            </div>
            <div className = "uniDescription">
              <h3>Computer Science Student At</h3>
              <h2> University of Ottawa (2023~ )</h2>
              <div className="description-linespacer"></div>
              <p>
                <span className="highlight-bold">RELEVANT COURSEWORK & EXPERIENCE</span><br />
                <span className="italic-text">[ TECHNICAL FOUNDATION ]</span><br />
                  • Software Engineering: Intro to Software Engineering, Software Requirements, Database I<br />
                  • Systems & Theory: Data Structures & Algorithms, Computer Architecture I<br />
                  • Mathematics: Calculus II<br />
                  <br />
                  <span className="italic-text">[ LEADERSHIP & INVOLVEMENT ]</span><br />
                  • Executive Board Member | Korean Student Association<br />
                    - Manage event operations and community engagement.<br />
                  • Active Member | Computer Science Club<br />
                    • Active Member | Badminton Club & Board Game Club<br />
                    </p>
            </div>
          </div>
        </div>
        
        <section id="skills" className="skills-container">
          <SkillsCarousel />
        </section>

        <section id="projects" className="projects-container">
          <div className="section-title">
            <h1 style={{ fontStyle: 'italic' }}>Projects</h1>
          </div>
          <ProjectCard />
        </section>

        <footer id="contact" className="footer">
          <p>This is the website to introduce me, Edward! (‾◡◝)</p>
          <p>Email: eoedward7353@gmail.com</p>
          <a href="https://www.instagram.com/edward99929/" target="_blank" rel="noopener noreferrer">
            <i class="fa-brands fa-instagram" aria-label="Instagram logo"></i>
          </a>
          <a href="https://github.com/Edward46282" target="_blank" rel="noopener noreferrer">
            <i class="fa-brands fa-github" aria-label="Github logo"></i>
          </a>
          <a href="https://www.linkedin.com/in/edward-eo-74068b2b5/" target="_blank" rel="noopener noreferrer">
            <i class="fa-brands fa-linkedin" aria-label="Linkedin logo"></i>
          </a>
        </footer>
      </div>

      {/* The Right-Side Navbar */}
      <nav className="side-nav">
        <ul>
          <li><a href="#mainScreen">Main Screen</a></li>
          <li><a href="#education">Education</a></li>
          <li><a href="#skills">Skills</a></li>
          <li><a href="#projects">Projects</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav>
    </div>
  )
}

function ImageCard() {
  const [index, setIndex] = useState(0); // index holds current value (0 at start) and setIndex is a function
  // called when value needs to be changed

  // Arrow function to go to next image
  const nextImage = () => {
    setIndex((prev) => (prev + 1) % images.length); // setIndex takes an arrow function that takes prev as a parameter (autofilled with current index value)
  };

  // Timer: Change every 15 seconds
  useEffect(() => {
    const timer = setInterval(nextImage, 15000);
    return () => clearInterval(timer); // Cleanup on unmount
  }, [index]);

  const nextIndex = (index + 1) % images.length;

  return (
    <div className="carousel-container">
      <div className="card-stack">
        {/* Main Image */}
        <img src={images[index]} key={index} className="main-card" alt="Active" />
        
        {/* Upcoming Tilted Image */}
        <img src={images[nextIndex]} key={nextIndex} className="next-card" alt="Next" />
      </div>
      
      <button onClick={nextImage} className="nav-button">Next Image</button>
    </div>
  );
}

function ProjectCard() {
  return (
    <div className = "project-Card-Container">
      {projects.map((project) => (
        <div key={project.id} className="project-card">
          <a href={project.address} target="_blank" rel="noopener noreferrer">
            <h3>{project.title}</h3>
            <p>{project.desc}</p>
          </a>
        </div>
      ))}
    </div>
  );
}

export default App