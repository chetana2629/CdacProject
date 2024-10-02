// src/App.js
import './App.css';
import { Contact } from './contact/contact';
import { Header } from './headerFooter/header';
import { Footer } from './headerFooter/footer';
import { RestrictedPage } from './restrictedPage/restricted';
import { Faq } from './faq/faq';
import { ProjectDetails } from './projectDetails/projectDetails';
import { About } from './about/about';
import { NotFound } from './404/404';
import { Services } from './servicesPage/services';
import { ServiceSingle } from './serviceSingle/serviceSingle';
import { Home } from './home/home';
import { Project } from './project/project';
import { Terms } from './Allterms/terms';
import { Privacy } from './Allterms/privacy';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import { SmoothScroll } from './smooth';
import Login from './login/Login';
import Register3 from './register/Register3';
import CustomerPage from './customer/CustomerPage';
import OrdersPage from './order/OrdersPage';
import DesignerPage from './designer/DesignerPage';
import AdminPage from './Admin/AdminPage'; // Import the AdminPage

function App() {
  return (
    <BrowserRouter>
      <SmoothScroll />
      <Header />
      <Routes>
        <Route path='/contact' element={<Contact />} />
        <Route path='/restricted-page' element={<RestrictedPage />} />
        <Route path='/faq' element={<Faq />} />
        <Route path='/project-details' element={<ProjectDetails />} />
        <Route path='/aboutus' element={<About />} />
        <Route path='/error' element={<NotFound />} />
        <Route path='/services' element={<Services />} />
        <Route path='/service-single' element={<ServiceSingle />} />
        <Route path='/' element={<Home />} />
        <Route path='/projects' element={<Project />} />
        <Route path='/terms' element={<Terms />} />
        <Route path='/privacy-policy' element={<Privacy />} />
        <Route path='/login' element={<Login />} />
        <Route path='/Register3' element={<Register3 />} />
        <Route path='/customer' element={<CustomerPage />} />
        <Route path='/orders' element={<OrdersPage />} />
        <Route path='/designer' element={<DesignerPage />} />
        <Route path='/admin' element={<AdminPage />} /> {/* Add route for AdminPage */}
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
