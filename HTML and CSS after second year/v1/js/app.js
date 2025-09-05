function getElement(selector) {
  const element = document.querySelector(selector) //document represents the whole page.
  //selects the first element that matches the selector

  if (element) return element
  throw Error(
    `Please double check your class names, there is no ${selector} class`
  )
}

const links = getElement('.nav-links')
const navBtnDOM = getElement('.nav-btn')

navBtnDOM.addEventListener('click', () => { //when someone clicks, do something
  links.classList.toggle('show-links') // toggle = on and off. So, it adds and remove the word show-links to the class of show-links
})

const date = getElement('#date')
const currentYear = new Date().getFullYear()
date.textContent = currentYear

