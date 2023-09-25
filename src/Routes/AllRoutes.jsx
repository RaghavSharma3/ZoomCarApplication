import { Route, Routes, BrowserRouter } from "react-router-dom";
import PrivateRoute from "../Components/PrivateRoute";
import BookingsPage from "../Pages/BookingsPage";
import HomePage from "../Pages/HomePage";
import LoginPage from "../Pages/LoginPage";
import SignUpPage from "../Pages/SignUpPage";
import CarPage from "../Pages/CarPage";
import ZmsPage from "../Pages/ZmsPage";
import Footer from "../Components/Footer";
import Payment from "../Pages/Payment";
import HostPage from "../Pages/HostPage";
import Navbar from "../Components/Navbar";
export default function AllRoutes() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/car" element={<CarPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/zoomcar-mobility-services" element={<ZmsPage />} />
        <Route path="/host" element={<HostPage />} />
        <Route path="/car-bookings" element={<BookingsPage />} />
        <Route path="/payment" element={<Payment />} />
        <Route
          path="/car-bookings"
          element={
            <PrivateRoute>
              <BookingsPage />
            </PrivateRoute>
          }
        />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}
