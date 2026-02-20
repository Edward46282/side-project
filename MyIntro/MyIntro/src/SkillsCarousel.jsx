import { useRef, useState, useEffect } from "react";
import { motion, useTransform, useScroll } from "framer-motion";
import "./Skills.css";

const SKILLS = [
  { id: 1, title: "Code in well-known programming language like Python and Java", desc: "Able to code complex problems in Python, Java, JavaScript, and more!" },
  { id: 2, title: "Data structure", desc: "Proficient in designing and implementing high-performance data structures and algorithms. Strong fundamentals in BST, Heaps, DFS, etc." },
  { id: 3, title: "Web development", desc: "Able to use React, HTML, CSS, and JavaScript to develop a website." },
  { id: 4, title: "Networking", desc: "Strong fundamentals in networking concepts, like the OSI model, through Cisco packet tracker, side projects and coursework. " },
];

const SkillsCarousel = () => {
  const targetRef = useRef(null);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 600);

  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth <= 600);
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  const { scrollYProgress } = useScroll({ target: targetRef });
  const targetX = isMobile ? ["0","-95%"] : ["0", "-45%"]; // -95 for mobile, -45 for desktop
  // Map scroll progress 0=top, 1=past the section -> horizontal movement (0 to -30%)
  // -70% move the cards to the left as you scroll down.
  const x = useTransform(scrollYProgress, [0, 1], targetX);

  return (
    <section ref={targetRef} className="skills-section">
      <h1 className="skills-title">Skills</h1>
      <div className="sticky-wrapper">
        <motion.div style={{ x }} className="cards-container">
          {SKILLS.map((skill) => (
            <div key={skill.id} className="skill-card">
              <h3>{skill.title}</h3>
              <p>{skill.desc}</p>
            </div>
          ))}
        </motion.div>
      </div>
    </section>
  );
};

export default SkillsCarousel;